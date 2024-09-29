package me.jddev0.epfd.mixin;

import me.jddev0.epfd.utils.CookingUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
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
    private void processCooking(RecipeHolder<CookingPotRecipe> recipe, CookingPotBlockEntity cookingPot,
                                CallbackInfoReturnable<Boolean> cir) {
        if(level == null)
            return;

        BlockPos heatSourcePos = worldPosition.below();
        BlockState heatSourceState = level.getBlockState(heatSourcePos);

        if(requiresDirectHeat() && !heatSourceState.is(ModTags.HEAT_SOURCES) &&
                heatSourceState.is(ModTags.HEAT_CONDUCTORS)) {
            heatSourcePos = heatSourcePos.below();
            heatSourceState = level.getBlockState(heatSourcePos);
        }

        int speedMultiplier = CookingUtils.getSpeedMultiplierForHeatSource(heatSourceState);
        if(speedMultiplier != -1)
            cookTime += speedMultiplier - 1;
    }
}
