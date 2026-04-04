package me.jddev0.epfd.datagen.loot;

import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTables extends FabricBlockLootTableProvider {
    public ModBlockLootTables(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(dataOutput, lookupProvider);
    }

    @Override
    public void generate() {
        dropSelf(EPFDBlocks.ELECTRIC_STOVE);
        dropSelf(EPFDBlocks.INDUCTION_STOVE);
    }
}
