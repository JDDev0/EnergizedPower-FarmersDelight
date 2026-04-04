package me.jddev0.epfd.block;

import com.mojang.serialization.MapCodec;
import me.jddev0.epfd.block.entity.EPFDBlockEntities;
import me.jddev0.epfd.block.entity.ElectricStoveBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ElectricStoveBlock extends AbstractStoveBlock {
    public static final MapCodec<ElectricStoveBlock> CODEC = simpleCodec(ElectricStoveBlock::new);

    protected ElectricStoveBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState state) {
        return new ElectricStoveBlockEntity(blockPos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, EPFDBlockEntities.ELECTRIC_STOVE_ENTITY, ElectricStoveBlockEntity::tick);
    }

    public static class Item extends BlockItem {
        public Item(Block block, net.minecraft.world.item.Item.Properties props) {
            super(block, props);
        }

        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> tooltip, TooltipFlag type) {
            if(Minecraft.getInstance().hasShiftDown()) {
                tooltip.accept(Component.translatable("tooltip.energizedpowerfd.stoves.txt.shift.1",
                        stack.getHoverName(),
                        ElectricStoveBlockEntity.RECIPE_DURATION_MULTIPLIER).withStyle(ChatFormatting.GRAY));
            }else {
                tooltip.accept(Component.translatable("tooltip.energizedpower.shift_details.txt").withStyle(ChatFormatting.YELLOW));
            }
        }
    }
}
