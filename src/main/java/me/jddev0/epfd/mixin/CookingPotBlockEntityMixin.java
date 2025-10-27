package me.jddev0.epfd.mixin;

import me.jddev0.epfd.utils.CookingUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.HeatableBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.tag.ModTags;

@Mixin(CookingPotBlockEntity.class)
public abstract class CookingPotBlockEntityMixin extends SyncedBlockEntity implements HeatableBlockEntity {
    @Shadow
    private int cookTime;

    public CookingPotBlockEntityMixin(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Inject(method = "processCooking", at = @At("HEAD"))
    private void processCooking(RecipeEntry<CookingPotRecipe> recipe, CookingPotBlockEntity cookingPot,
                                CallbackInfoReturnable<Boolean> cir) {
        if(world == null)
            return;

        BlockPos heatSourcePos = pos.down();
        BlockState heatSourceState = world.getBlockState(heatSourcePos);

        if(requiresDirectHeat() && !heatSourceState.isIn(ModTags.HEAT_SOURCES) &&
                heatSourceState.isIn(ModTags.HEAT_CONDUCTORS)) {
            heatSourcePos = heatSourcePos.down();
            heatSourceState = world.getBlockState(heatSourcePos);
        }

        int speedMultiplier = CookingUtils.getSpeedMultiplierForHeatSource(heatSourceState);
        if(speedMultiplier != -1)
            cookTime += speedMultiplier - 1;
    }
}
