package me.jddev0.epfd.block.entity;

import me.jddev0.ep.config.ModConfigs;
import me.jddev0.ep.energy.EnergizedPowerEnergyStorage;
import me.jddev0.ep.energy.EnergizedPowerLimitingEnergyStorage;
import me.jddev0.ep.machine.configuration.RedstoneMode;
import me.jddev0.ep.machine.upgrade.UpgradeModuleModifier;
import me.jddev0.ep.util.EnergyUtils;
import me.jddev0.epfd.block.entity.base.ConfigurableUpgradableEnergyStorageBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;

public abstract class AbstractStoveBlockEntity extends ConfigurableUpgradableEnergyStorageBlockEntity<EnergizedPowerEnergyStorage> {
    protected final long baseEnergyConsumptionPerTick;
    protected boolean hasEnoughEnergy;

    protected int timeoutOffState;

    public AbstractStoveBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
                                    String machineName,
                                    long baseEnergyCapacity, long baseEnergyTransferRate,
                                    long baseEnergyConsumptionPerTick) {
        super(
                type, blockPos, blockState,

                machineName, baseEnergyCapacity, baseEnergyTransferRate,

                UpgradeModuleModifier.ENERGY_CONSUMPTION,
                UpgradeModuleModifier.ENERGY_CAPACITY
        );

        this.baseEnergyConsumptionPerTick = baseEnergyConsumptionPerTick;
    }

    @Override
    protected EnergizedPowerEnergyStorage initEnergyStorage() {
        return new EnergizedPowerEnergyStorage(baseEnergyCapacity, baseEnergyCapacity, baseEnergyCapacity) {
            @Override
            public long getCapacity() {
                return Math.max(1, (long)Math.ceil(capacity * upgradeModuleInventory.getModifierEffectProduct(
                        UpgradeModuleModifier.ENERGY_CAPACITY)));
            }

            @Override
            protected void onFinalCommit() {
                markDirty();
                syncEnergyToPlayers(32);
            }
        };
    }

    @Override
    protected EnergizedPowerLimitingEnergyStorage initLimitingEnergyStorage() {
        return new EnergizedPowerLimitingEnergyStorage(energyStorage, baseEnergyTransferRate, 0) {
            @Override
            public long getMaxInsert() {
                return Math.max(1, (long)Math.ceil(maxInsert * upgradeModuleInventory.getModifierEffectProduct(
                        UpgradeModuleModifier.ENERGY_TRANSFER_RATE)));
            }
        };
    }

    @Override
    protected PropertyDelegate initContainerData() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> redstoneMode.ordinal();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> redstoneMode = RedstoneMode.fromIndex(value);
                }
            }

            @Override
            public int size() {
                return 1;
            }
        };
    }

    public Text getDisplayName() {
        return Text.translatable("container.energizedpowerfd." + this.machineName);
    }

    public int getRedstoneOutput() {
        return EnergyUtils.getRedstoneSignalFromEnergyStorage(energyStorage);
    }

    protected void litBlock() {
        if(world.getBlockState(getPos()).contains(Properties.LIT) &&
                !world.getBlockState(getPos()).get(Properties.LIT)) {
            world.setBlockState(getPos(), getCachedState().with(Properties.LIT, true), 3);
        }
    }

    protected void unlitBlock() {
        if(world.getBlockState(getPos()).contains(Properties.LIT) &&
                world.getBlockState(getPos()).get(Properties.LIT)) {
            world.setBlockState(getPos(), getCachedState().with(Properties.LIT, false), 3);
        }
    }

    public static void tick(World level, BlockPos blockPos, BlockState state, AbstractStoveBlockEntity blockEntity) {
        if(level.isClient())
            return;

        if(blockEntity.timeoutOffState > 0) {
            blockEntity.timeoutOffState--;

            if(blockEntity.timeoutOffState == 0) {
                blockEntity.unlitBlock();
                markDirty(level, blockPos, state);
            }
        }

        if(!blockEntity.redstoneMode.isActive(state.get(Properties.POWERED))) {
            blockEntity.unlitBlock();

            return;
        }

        if(blockEntity.hasSkilletOrCookingPot()) {
            int energyConsumptionPerTick = Math.max(1, (int)Math.ceil(blockEntity.baseEnergyConsumptionPerTick *
                    blockEntity.upgradeModuleInventory.getModifierEffectProduct(UpgradeModuleModifier.ENERGY_CONSUMPTION)));

            if(energyConsumptionPerTick <= blockEntity.energyStorage.getAmount()) {
                blockEntity.hasEnoughEnergy = true;
                blockEntity.timeoutOffState = 0;
                blockEntity.litBlock();

                try(Transaction transaction = Transaction.openOuter()) {
                    blockEntity.energyStorage.extract(energyConsumptionPerTick, transaction);
                    transaction.commit();
                }

                markDirty(level, blockPos, state);
            }else {
                blockEntity.hasEnoughEnergy = false;
                if(blockEntity.timeoutOffState == 0) {
                    blockEntity.timeoutOffState = ModConfigs.COMMON_OFF_STATE_TIMEOUT.getValue();
                }
                markDirty(level, blockPos, state);
            }
        }else {
            if(blockEntity.timeoutOffState == 0) {
                blockEntity.timeoutOffState = ModConfigs.COMMON_OFF_STATE_TIMEOUT.getValue();
            }
            markDirty(level, blockPos, state);
        }
    }

    private boolean hasSkilletOrCookingPot() {
        BlockPos testPos = pos.up();
        BlockState testState = world.getBlockState(testPos);

        if(testState.isIn(ModTags.HEAT_CONDUCTORS)) {
            testPos = testPos.up();
            testState = world.getBlockState(testPos);
        }

        return testState.isOf(ModBlocks.SKILLET.get()) || testState.isOf(ModBlocks.COOKING_POT.get());
    }
}
