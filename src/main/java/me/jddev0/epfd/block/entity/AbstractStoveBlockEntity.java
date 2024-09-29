package me.jddev0.epfd.block.entity;

import me.jddev0.ep.energy.ReceiveOnlyEnergyStorage;
import me.jddev0.ep.machine.configuration.RedstoneMode;
import me.jddev0.ep.machine.upgrade.UpgradeModuleModifier;
import me.jddev0.ep.util.EnergyUtils;
import me.jddev0.epfd.block.entity.base.ConfigurableUpgradableEnergyStorageBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;

public abstract class AbstractStoveBlockEntity extends ConfigurableUpgradableEnergyStorageBlockEntity<ReceiveOnlyEnergyStorage> {
    protected final int baseEnergyConsumptionPerTick;
    protected boolean hasEnoughEnergy;

    public AbstractStoveBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
                                    String machineName,
                                    int baseEnergyCapacity, int baseEnergyTransferRate,
                                    int baseEnergyConsumptionPerTick) {
        super(
                type, blockPos, blockState,

                machineName, baseEnergyCapacity, baseEnergyTransferRate,

                UpgradeModuleModifier.ENERGY_CONSUMPTION,
                UpgradeModuleModifier.ENERGY_CAPACITY
        );

        this.baseEnergyConsumptionPerTick = baseEnergyConsumptionPerTick;
    }

    @Override
    protected ReceiveOnlyEnergyStorage initEnergyStorage() {
        return new ReceiveOnlyEnergyStorage(0, baseEnergyCapacity, baseEnergyTransferRate) {
            @Override
            public int getCapacity() {
                return Math.max(1, (int)Math.ceil(capacity * upgradeModuleInventory.getModifierEffectProduct(
                        UpgradeModuleModifier.ENERGY_CAPACITY)));
            }

            @Override
            public int getMaxReceive() {
                return Math.max(1, (int)Math.ceil(maxReceive * upgradeModuleInventory.getModifierEffectProduct(
                        UpgradeModuleModifier.ENERGY_TRANSFER_RATE)));
            }

            @Override
            protected void onChange() {
                setChanged();
                syncEnergyToPlayers(32);
            }
        };
    }

    @Override
    protected ContainerData initContainerData() {
        return new ContainerData() {
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
            public int getCount() {
                return 1;
            }
        };
    }

    public Component getDisplayName() {
        return Component.translatable("container.energizedpowerfd." + this.machineName);
    }

    public int getRedstoneOutput() {
        return EnergyUtils.getRedstoneSignalFromEnergyStorage(energyStorage);
    }

    protected void litBlock() {
        if(level.getBlockState(getBlockPos()).hasProperty(BlockStateProperties.LIT) &&
                !level.getBlockState(getBlockPos()).getValue(BlockStateProperties.LIT)) {
            level.setBlock(getBlockPos(), getBlockState().setValue(BlockStateProperties.LIT, true), 3);
        }
    }

    protected void unlitBlock() {
        if(level.getBlockState(getBlockPos()).hasProperty(BlockStateProperties.LIT) &&
                level.getBlockState(getBlockPos()).getValue(BlockStateProperties.LIT)) {
            level.setBlock(getBlockPos(), getBlockState().setValue(BlockStateProperties.LIT, false), 3);
        }
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, AbstractStoveBlockEntity blockEntity) {
        if(level.isClientSide)
            return;

        if(!blockEntity.redstoneMode.isActive(state.getValue(BlockStateProperties.POWERED))) {
            blockEntity.unlitBlock();

            return;
        }

        if(blockEntity.hasSkilletOrCookingPot()) {
            int energyConsumptionPerTick = Math.max(1, (int)Math.ceil(blockEntity.baseEnergyConsumptionPerTick *
                    blockEntity.upgradeModuleInventory.getModifierEffectProduct(UpgradeModuleModifier.ENERGY_CONSUMPTION)));

            if(energyConsumptionPerTick <= blockEntity.energyStorage.getEnergy()) {
                blockEntity.hasEnoughEnergy = true;
                blockEntity.litBlock();

                blockEntity.energyStorage.setEnergy(blockEntity.energyStorage.getEnergy() - energyConsumptionPerTick);

                setChanged(level, blockPos, state);
            }else {
                blockEntity.hasEnoughEnergy = false;
                blockEntity.unlitBlock();
                setChanged(level, blockPos, state);
            }
        }else {
            blockEntity.unlitBlock();
            setChanged(level, blockPos, state);
        }
    }

    private boolean hasSkilletOrCookingPot() {
        BlockPos testPos = worldPosition.above();
        BlockState testState = level.getBlockState(testPos);

        if(testState.is(ModTags.HEAT_CONDUCTORS)) {
            testPos = testPos.above();
            testState = level.getBlockState(testPos);
        }

        return testState.is(ModBlocks.SKILLET.get()) || testState.is(ModBlocks.COOKING_POT.get());
    }
}
