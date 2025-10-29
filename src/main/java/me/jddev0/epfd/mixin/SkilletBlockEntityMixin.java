package me.jddev0.epfd.mixin;

import me.jddev0.epfd.utils.CookingUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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

    @Inject(method = "cookAndOutputItems", at = @At("HEAD"), remap = false)
    private void cookAndOutputItems(ItemStack cookingStack, World level, CallbackInfo ci) {
        if(level == null)
            return;

        BlockPos heatSourcePos = pos.down();
        BlockState heatSourceState = level.getBlockState(heatSourcePos);

        if(!requiresDirectHeat() && !heatSourceState.isIn(ModTags.HEAT_SOURCES) &&
                heatSourceState.isIn(ModTags.HEAT_CONDUCTORS)) {
            heatSourcePos = heatSourcePos.down();
            heatSourceState = level.getBlockState(heatSourcePos);
        }

        int speedMultiplier = CookingUtils.getSpeedMultiplierForHeatSource(heatSourceState);
        if(speedMultiplier != -1)
            cookingTime += speedMultiplier - 1;
    }
}
