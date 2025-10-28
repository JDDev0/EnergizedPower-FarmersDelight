package me.jddev0.epfd.block.entity;

import me.jddev0.epfd.config.ModConfigs;
import me.jddev0.epfd.screen.ElectricStoveMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ElectricStoveBlockEntity extends AbstractStoveBlockEntity {
    public static final int RECIPE_DURATION_MULTIPLIER = ModConfigs.COMMON_ELECTRIC_STOVE_RECIPE_DURATION_MULTIPLIER.getValue();

    public ElectricStoveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(
                EPFDBlockEntities.ELECTRIC_STOVE_ENTITY.get(), blockPos, blockState,

                "electric_stove",

                ModConfigs.COMMON_ELECTRIC_STOVE_CAPACITY.getValue(),
                ModConfigs.COMMON_ELECTRIC_STOVE_TRANSFER_RATE.getValue(),
                ModConfigs.COMMON_ELECTRIC_STOVE_ENERGY_CONSUMPTION_PER_TICK.getValue()
        );
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        syncEnergyToPlayer(player);

        return new ElectricStoveMenu(id, inventory, this, upgradeModuleInventory, data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyStorage.cast();
        }

        return super.getCapability(cap, side);
    }
}