package me.jddev0.epfd.block.entity.base;

import me.jddev0.ep.block.entity.base.UpgradableEnergyStorageBlockEntity;
import me.jddev0.ep.energy.IEnergizedPowerEnergyStorage;
import me.jddev0.ep.machine.configuration.IRedstoneModeHandler;
import me.jddev0.ep.machine.configuration.RedstoneMode;
import me.jddev0.ep.machine.configuration.RedstoneModeUpdate;
import me.jddev0.ep.machine.upgrade.UpgradeModuleModifier;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public abstract class ConfigurableUpgradableEnergyStorageBlockEntity
        <E extends IEnergizedPowerEnergyStorage>
        extends UpgradableEnergyStorageBlockEntity<E>
        implements RedstoneModeUpdate, IRedstoneModeHandler {
    protected @NotNull RedstoneMode redstoneMode = RedstoneMode.IGNORE;

    public ConfigurableUpgradableEnergyStorageBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
                                                          String machineName,
                                                          long baseEnergyCapacity, long baseEnergyTransferRate,
                                                          UpgradeModuleModifier... upgradeModifierSlots) {
        super(type, blockPos, blockState, machineName, baseEnergyCapacity, baseEnergyTransferRate,
                upgradeModifierSlots);
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);

        view.putInt("configuration.redstone_mode", redstoneMode.ordinal());
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);

        redstoneMode = RedstoneMode.fromIndex(view.getInt("configuration.redstone_mode", 0));
    }

    @Override
    public void setNextRedstoneMode() {
        redstoneMode = RedstoneMode.fromIndex(redstoneMode.ordinal() + 1);
        markDirty();
    }

    @Override
    @NotNull
    public RedstoneMode @NotNull [] getAvailableRedstoneModes() {
        return RedstoneMode.values();
    }

    @Override
    @NotNull
    public RedstoneMode getRedstoneMode() {
        return redstoneMode;
    }

    @Override
    public boolean setRedstoneMode(@NotNull RedstoneMode redstoneMode) {
        this.redstoneMode = redstoneMode;
        markDirty();

        return true;
    }
}