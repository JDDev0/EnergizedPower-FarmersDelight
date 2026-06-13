package me.jddev0.epfd.datagen;

import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.ElectricStoveBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
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

    private ResourceLocation orientableBlockModel(Block block, boolean uniqueBottomTexture) {
        return orientableBlockModel(block, "", "_top", uniqueBottomTexture?"_bottom":"_top",
                "_front", "_side");
    }

    private ResourceLocation orientableBlockModel(Block block, String fileSuffix, String topSuffix,
                                            String bottomSuffix, String frontSuffix, String sideSuffix) {
        return TexturedModel.createDefault(unused -> new TextureMapping().
                        put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, topSuffix)).
                        put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, bottomSuffix)).
                        put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, frontSuffix)).
                        put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, sideSuffix)).
                        copySlot(TextureSlot.TOP, TextureSlot.PARTICLE),
                ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM).get(block).createWithSuffix(block, fileSuffix, generator.modelOutput);
    }

    private void activatableOrientableBlockWithItem(Block block, ResourceLocation modelNormal,
                                                    ResourceLocation modelActive, BooleanProperty isActiveProperty) {
        generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).
                with(PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, isActiveProperty).
                        select(Direction.NORTH, false, Variant.variant().
                                with(VariantProperties.MODEL, modelNormal)).
                        select(Direction.NORTH, true, Variant.variant().
                                with(VariantProperties.MODEL, modelActive)).
                        select(Direction.SOUTH, false, Variant.variant().
                                with(VariantProperties.MODEL, modelNormal).
                                with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).
                        select(Direction.SOUTH, true, Variant.variant().
                                with(VariantProperties.MODEL, modelActive).
                                with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).
                        select(Direction.EAST, false, Variant.variant().
                                with(VariantProperties.MODEL, modelNormal).
                                with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).
                        select(Direction.EAST, true, Variant.variant().
                                with(VariantProperties.MODEL, modelActive).
                                with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).
                        select(Direction.WEST, false, Variant.variant().
                                with(VariantProperties.MODEL, modelNormal).
                                with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)).
                        select(Direction.WEST, true, Variant.variant().
                                with(VariantProperties.MODEL, modelActive).
                                with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                ));

        generator.delegateItemModel(block.asItem(), modelNormal);
    }
}
