package me.jddev0.epfd.datagen;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EnergizedPowerFDMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        
        tag(BlockTags.MINEABLE_WITH_PICKAXE).
                add(
                        ModBlocks.ELECTRIC_STOVE.get(),
                        ModBlocks.INDUCTION_STOVE.get()
                );

        tag(BlockTags.NEEDS_STONE_TOOL).
                add(
                        ModBlocks.ELECTRIC_STOVE.get(),
                        ModBlocks.INDUCTION_STOVE.get()
                );

        
        tag(ModTags.HEAT_SOURCES).
                add(ModBlocks.ELECTRIC_STOVE.get(),
                        ModBlocks.INDUCTION_STOVE.get());
    }
}
