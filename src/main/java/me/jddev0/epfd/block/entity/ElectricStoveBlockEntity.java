package me.jddev0.epfd.block.entity;

import me.jddev0.epfd.config.ModConfigs;
import me.jddev0.epfd.screen.ElectricStoveMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

public class ElectricStoveBlockEntity extends AbstractStoveBlockEntity {
    public static final int RECIPE_DURATION_MULTIPLIER = ModConfigs.COMMON_ELECTRIC_STOVE_RECIPE_DURATION_MULTIPLIER.getValue();

    public ElectricStoveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(
                ModBlockEntities.ELECTRIC_STOVE_ENTITY.get(), blockPos, blockState,

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

    public @Nullable IEnergyStorage getEnergyStorageCapability(@Nullable Direction side) {
        return energyStorage;
    }
}