package me.jddev0.epfd.block;

import com.mojang.serialization.MapCodec;
import me.jddev0.epfd.block.entity.ElectricStoveBlockEntity;
import me.jddev0.epfd.block.entity.EPFDBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ElectricStoveBlock extends AbstractStoveBlock {
    public static final MapCodec<ElectricStoveBlock> CODEC = createCodec(ElectricStoveBlock::new);

    protected ElectricStoveBlock(Settings props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState state) {
        return new ElectricStoveBlockEntity(blockPos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World level, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, EPFDBlockEntities.ELECTRIC_STOVE_ENTITY, ElectricStoveBlockEntity::tick);
    }

    public static class Item extends BlockItem {
        public Item(Block block, Settings props) {
            super(block, props);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> tooltip, TooltipType type) {
            if(MinecraftClient.getInstance().isShiftPressed()) {
                tooltip.accept(Text.translatable("tooltip.energizedpowerfd.stoves.txt.shift.1",
                        stack.getName(),
                        ElectricStoveBlockEntity.RECIPE_DURATION_MULTIPLIER).formatted(Formatting.GRAY));
            }else {
                tooltip.accept(Text.translatable("tooltip.energizedpower.shift_details.txt").formatted(Formatting.YELLOW));
            }
        }
    }
}
