package me.jddev0.epfd.datagen;

import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookupProvider) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).
                add(
                        EPFDBlocks.ELECTRIC_STOVE,
                        EPFDBlocks.INDUCTION_STOVE
                );

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).
                add(
                        EPFDBlocks.ELECTRIC_STOVE,
                        EPFDBlocks.INDUCTION_STOVE
                );


        getOrCreateTagBuilder(ModTags.HEAT_SOURCES).
                add(EPFDBlocks.ELECTRIC_STOVE,
                        EPFDBlocks.INDUCTION_STOVE);
    }
}
