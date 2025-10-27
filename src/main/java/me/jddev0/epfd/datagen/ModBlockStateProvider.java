package me.jddev0.epfd.datagen;

import me.jddev0.epfd.block.ElectricStoveBlock;
import me.jddev0.epfd.block.EPFDBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
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
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).
                coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, isActiveProperty).
                        register(Direction.NORTH, false, BlockStateVariant.create().
                                put(VariantSettings.MODEL, modelNormal)).
                        register(Direction.NORTH, true, BlockStateVariant.create().
                                put(VariantSettings.MODEL, modelActive)).
                        register(Direction.SOUTH, false, BlockStateVariant.create().
                                put(VariantSettings.MODEL, modelNormal).
                                put(VariantSettings.Y, VariantSettings.Rotation.R180)).
                        register(Direction.SOUTH, true, BlockStateVariant.create().
                                put(VariantSettings.MODEL, modelActive).
                                put(VariantSettings.Y, VariantSettings.Rotation.R180)).
                        register(Direction.EAST, false, BlockStateVariant.create().
                                put(VariantSettings.MODEL, modelNormal).
                                put(VariantSettings.Y, VariantSettings.Rotation.R90)).
                        register(Direction.EAST, true, BlockStateVariant.create().
                                put(VariantSettings.MODEL, modelActive).
                                put(VariantSettings.Y, VariantSettings.Rotation.R90)).
                        register(Direction.WEST, false, BlockStateVariant.create().
                                put(VariantSettings.MODEL, modelNormal).
                                put(VariantSettings.Y, VariantSettings.Rotation.R270)).
                        register(Direction.WEST, true, BlockStateVariant.create().
                                put(VariantSettings.MODEL, modelActive).
                                put(VariantSettings.Y, VariantSettings.Rotation.R270))
                ));

        generator.registerParentedItemModel(block.asItem(), modelNormal);
    }
}
