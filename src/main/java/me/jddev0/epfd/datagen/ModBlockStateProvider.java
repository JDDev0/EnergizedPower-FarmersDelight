package me.jddev0.epfd.datagen;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.block.ElectricStoveBlock;
import me.jddev0.epfd.block.EPFDBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EnergizedPowerFDMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerBlocks();
    }

    private void registerBlocks() {
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

    private ModelFile orientableBlockModel(RegistryObject<? extends Block> block, boolean uniqueBottomTexture) {
        return orientableBlockModel(block, "", "_top", uniqueBottomTexture?"_bottom":"_top",
                "_front", "_side");
    }

    private ModelFile orientableBlockModel(RegistryObject<? extends Block> block, String fileSuffix, String topSuffix,
                                           String bottomSuffix, String frontSuffix, String sideSuffix) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return models().
                withExistingParent(blockId.getPath() + fileSuffix, ModelProvider.BLOCK_FOLDER + "/orientable").
                texture("particle", "#top").
                texture("top", getBlockTexture(block, topSuffix)).
                texture("bottom", getBlockTexture(block, bottomSuffix)).
                texture("front", getBlockTexture(block, frontSuffix)).
                texture("side", getBlockTexture(block, sideSuffix));
    }

    private void activatableOrientableBlockWithItem(RegistryObject<? extends Block> block, ModelFile modelNormal,
                                                    ModelFile modelActive, BooleanProperty isActiveProperty) {
        getVariantBuilder(block.get()).partialState().
                with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).with(isActiveProperty, false).modelForState().
                modelFile(modelNormal).addModel().partialState().
                with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).with(isActiveProperty, true).modelForState().
                modelFile(modelActive).addModel().partialState().
                with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).with(isActiveProperty, false).modelForState().
                rotationY(180).modelFile(modelNormal).addModel().partialState().
                with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).with(isActiveProperty, true).modelForState().
                rotationY(180).modelFile(modelActive).addModel().partialState().
                with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST).with(isActiveProperty, false).modelForState().
                rotationY(90).modelFile(modelNormal).addModel().partialState().
                with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST).with(isActiveProperty, true).modelForState().
                rotationY(90).modelFile(modelActive).addModel().partialState().
                with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST).with(isActiveProperty, false).modelForState().
                rotationY(270).modelFile(modelNormal).addModel().partialState().
                with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST).with(isActiveProperty, true).modelForState().
                rotationY(270).modelFile(modelActive).addModel().partialState();

        simpleBlockItem(block.get(), modelNormal);
    }

    private ResourceLocation getBlockTexture(RegistryObject<? extends Block> block, String pathSuffix) {
        ResourceLocation blockId = block.getId();

        return new ResourceLocation(blockId.getNamespace(),
                ModelProvider.BLOCK_FOLDER + "/" + blockId.getPath() + pathSuffix);
    }
}
