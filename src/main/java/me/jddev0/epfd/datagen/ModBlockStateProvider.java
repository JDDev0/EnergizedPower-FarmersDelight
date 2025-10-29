package me.jddev0.epfd.datagen;

import me.jddev0.epfd.block.ElectricStoveBlock;
import me.jddev0.epfd.block.EPFDBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.Direction;

class ModBlockStateProvider {
    private final BlockStateModelGenerator generator;

    ModBlockStateProvider(BlockStateModelGenerator generator) {
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
        return TexturedModel.makeFactory(unused -> new TextureMap().
                        put(TextureKey.TOP, TextureMap.getSubId(block, topSuffix)).
                        put(TextureKey.BOTTOM, TextureMap.getSubId(block, bottomSuffix)).
                        put(TextureKey.FRONT, TextureMap.getSubId(block, frontSuffix)).
                        put(TextureKey.SIDE, TextureMap.getSubId(block, sideSuffix)).
                        copy(TextureKey.TOP, TextureKey.PARTICLE),
                Models.ORIENTABLE_WITH_BOTTOM).get(block).upload(block, fileSuffix, generator.modelCollector);
    }

    private void activatableOrientableBlockWithItem(Block block, Identifier modelNormal,
                                                    Identifier modelActive, BooleanProperty isActiveProperty) {
        generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(block).
                with(BlockStateVariantMap.models(isActiveProperty).
                        register(false, new WeightedVariant(Pool.of(new ModelVariant(modelNormal)))).
                        register(true, new WeightedVariant(Pool.of(new ModelVariant(modelActive))))).
                coordinate(BlockStateVariantMap.operations(Properties.HORIZONTAL_FACING).
                        register(Direction.NORTH, BlockStateModelGenerator.NO_OP).
                        register(Direction.SOUTH, BlockStateModelGenerator.ROTATE_Y_180).
                        register(Direction.EAST, BlockStateModelGenerator.ROTATE_Y_90).
                        register(Direction.WEST, BlockStateModelGenerator.ROTATE_Y_270)
                ));

        generator.registerParentedItemModel(block, modelNormal);
    }
}
