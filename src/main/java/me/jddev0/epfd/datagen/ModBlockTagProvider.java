package me.jddev0.epfd.datagen;

import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).
                add(
                        EPFDBlocks.ELECTRIC_STOVE,
                        EPFDBlocks.INDUCTION_STOVE
                );

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).
                add(
                        EPFDBlocks.ELECTRIC_STOVE,
                        EPFDBlocks.INDUCTION_STOVE
                );


        getOrCreateTagBuilder(ModTags.Blocks.HEAT_SOURCES).
                add(EPFDBlocks.ELECTRIC_STOVE,
                        EPFDBlocks.INDUCTION_STOVE);
    }
}
