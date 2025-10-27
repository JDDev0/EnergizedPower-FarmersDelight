package me.jddev0.epfd.block.entity;

import me.jddev0.epfd.config.ModConfigs;
import me.jddev0.epfd.screen.InductionStoveMenu;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class InductionStoveBlockEntity extends AbstractStoveBlockEntity {
    public static final int RECIPE_DURATION_MULTIPLIER = ModConfigs.COMMON_INDUCTION_STOVE_RECIPE_DURATION_MULTIPLIER.getValue();

    public InductionStoveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(
                EPFDBlockEntities.INDUCTION_STOVE_ENTITY, blockPos, blockState,

                "induction_stove",

                ModConfigs.COMMON_INDUCTION_STOVE_CAPACITY.getValue(),
                ModConfigs.COMMON_INDUCTION_STOVE_TRANSFER_RATE.getValue(),
                ModConfigs.COMMON_INDUCTION_STOVE_ENERGY_CONSUMPTION_PER_TICK.getValue()
        );
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
        syncEnergyToPlayer(player);

        return new InductionStoveMenu(id, this, inventory, upgradeModuleInventory, data);
    }
}