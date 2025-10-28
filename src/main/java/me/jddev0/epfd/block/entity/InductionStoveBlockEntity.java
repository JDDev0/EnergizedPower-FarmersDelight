package me.jddev0.epfd.block.entity;

import me.jddev0.epfd.config.ModConfigs;
import me.jddev0.epfd.screen.InductionStoveMenu;
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

public class InductionStoveBlockEntity extends AbstractStoveBlockEntity {
    public static final int RECIPE_DURATION_MULTIPLIER = ModConfigs.COMMON_INDUCTION_STOVE_RECIPE_DURATION_MULTIPLIER.getValue();

    public InductionStoveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(
                EPFDBlockEntities.INDUCTION_STOVE_ENTITY.get(), blockPos, blockState,

                "induction_stove",

                ModConfigs.COMMON_INDUCTION_STOVE_CAPACITY.getValue(),
                ModConfigs.COMMON_INDUCTION_STOVE_TRANSFER_RATE.getValue(),
                ModConfigs.COMMON_INDUCTION_STOVE_ENERGY_CONSUMPTION_PER_TICK.getValue()
        );
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        syncEnergyToPlayer(player);

        return new InductionStoveMenu(id, inventory, this, upgradeModuleInventory, data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyStorage.cast();
        }

        return super.getCapability(cap, side);
    }
}