package me.jddev0.epfd.datagen.loot;

import me.jddev0.epfd.block.EPFDBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return EPFDBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    @Override
    protected void generate() {
        dropSelf(EPFDBlocks.ELECTRIC_STOVE);
        dropSelf(EPFDBlocks.INDUCTION_STOVE);
    }

    private void dropSelf(RegistryObject<? extends Block> block) {
        dropSelf(block.get());
    }
}
