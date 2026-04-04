package me.jddev0.epfd.datagen;

import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.ElectricStoveBlock;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

class ModBlockStateProvider {
    private final BlockModelGenerators generator;

    ModBlockStateProvider(BlockModelGenerators generator) {
        this.generator = generator;
    }

    void registerBlocks() {
        activatableOrientableBlockWithItem(EPFDBlocks.ELECTRIC_STOVE,
                orientableBlockModel(EPFDBlocks.ELECTRIC_STOVE, true),
                orientableBlockModel(EPFDBlocks.ELECTRIC_STOVE, "_on", "_top_on", "_bottom",
                        "_front_on", "_side"),
                ElectricStoveBlock.LIT);

        activatableOrientableBlockWithItem(EPFDBlocks.INDUCTION_STOVE,
                orientableBlockModel(EPFDBlocks.INDUCTION_STOVE, true),
                orientableBlockModel(EPFDBlocks.INDUCTION_STOVE, "_on", "_top_on", "_bottom",
                        "_front_on", "_side"),
                ElectricStoveBlock.LIT);
    }

    private Identifier orientableBlockModel(Block block, boolean uniqueBottomTexture) {
        return orientableBlockModel(block, "", "_top", uniqueBottomTexture?"_bottom":"_top",
                "_front", "_side");
    }

    private Identifier orientableBlockModel(Block block, String fileSuffix, String topSuffix,
                                            String bottomSuffix, String frontSuffix, String sideSuffix) {
        return TexturedModel.createDefault(unused -> new TextureMapping().
                        put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, topSuffix)).
                        put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, bottomSuffix)).
                        put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, frontSuffix)).
                        put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, sideSuffix)).
                        copySlot(TextureSlot.TOP, TextureSlot.PARTICLE),
                ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM).get(block).createWithSuffix(block, fileSuffix, generator.modelOutput);
    }

    private void activatableOrientableBlockWithItem(Block block, Identifier modelNormal,
                                                    Identifier modelActive, BooleanProperty isActiveProperty) {
        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).
                with(PropertyDispatch.initial(isActiveProperty).
                        select(false, new MultiVariant(WeightedList.of(new Variant(modelNormal)))).
                        select(true, new MultiVariant(WeightedList.of(new Variant(modelActive))))).
                with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING).
                        select(Direction.NORTH, BlockModelGenerators.NOP).
                        select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180).
                        select(Direction.EAST, BlockModelGenerators.Y_ROT_90).
                        select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
                ));

        generator.registerSimpleItemModel(block, modelNormal);
    }
}
