package me.jddev0.epfd.datagen;

import me.jddev0.ep.datagen.ModBlockTagProvider.TagBuilderFix;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        buildTag(BlockTags.MINEABLE_WITH_PICKAXE).
                add(
                        EPFDBlocks.ELECTRIC_STOVE,
                        EPFDBlocks.INDUCTION_STOVE
                );

        buildTag(BlockTags.NEEDS_STONE_TOOL).
                add(
                        EPFDBlocks.ELECTRIC_STOVE,
                        EPFDBlocks.INDUCTION_STOVE
                );


        buildTag(ModTags.Blocks.HEAT_SOURCES).
                add(EPFDBlocks.ELECTRIC_STOVE,
                        EPFDBlocks.INDUCTION_STOVE);
    }

    private TagBuilderFix buildTag(final TagKey<Block> tagKey) {
        return new TagBuilderFix(tag(tagKey));
    }
}
