package me.jddev0.epfd.utils;

import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.entity.ElectricStoveBlockEntity;
import me.jddev0.epfd.block.entity.InductionStoveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class CookingUtils {
    private CookingUtils() {}

    public static int getSpeedMultiplierForHeatSource(BlockState state) {
        if(state.is(EPFDBlocks.ELECTRIC_STOVE))
            return ElectricStoveBlockEntity.RECIPE_DURATION_MULTIPLIER;

        if(state.is(EPFDBlocks.INDUCTION_STOVE))
            return InductionStoveBlockEntity.RECIPE_DURATION_MULTIPLIER;

        return -1;
    }
}
