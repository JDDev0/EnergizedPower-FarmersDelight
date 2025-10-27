package me.jddev0.epfd.mixin;

import me.jddev0.epfd.utils.CookingUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.entity.HeatableBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.tag.ModTags;

@Mixin(SkilletBlockEntity.class)
public abstract class SkilletBlockEntityMixin extends SyncedBlockEntity implements HeatableBlockEntity {
    @Shadow
    private int cookingTime;

    public SkilletBlockEntityMixin(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Inject(method = "cookAndOutputItems", at = @At("HEAD"))
    private void cookAndOutputItems(ItemStack cookingStack, Level level, CallbackInfo ci) {
        if(level == null)
            return;

        BlockPos heatSourcePos = worldPosition.below();
        BlockState heatSourceState = level.getBlockState(heatSourcePos);

        if(!requiresDirectHeat() && !heatSourceState.is(ModTags.HEAT_SOURCES) &&
                heatSourceState.is(ModTags.HEAT_CONDUCTORS)) {
            heatSourcePos = heatSourcePos.below();
            heatSourceState = level.getBlockState(heatSourcePos);
        }

        int speedMultiplier = CookingUtils.getSpeedMultiplierForHeatSource(heatSourceState);
        if(speedMultiplier != -1)
            cookingTime += speedMultiplier - 1;
    }
}
