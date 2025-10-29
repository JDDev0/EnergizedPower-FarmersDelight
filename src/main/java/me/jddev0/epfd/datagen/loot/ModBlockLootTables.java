package me.jddev0.epfd.datagen.loot;

import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;

public class ModBlockLootTables extends FabricBlockLootTableProvider {
    public ModBlockLootTables(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        dropSelf(EPFDBlocks.ELECTRIC_STOVE);
        dropSelf(EPFDBlocks.INDUCTION_STOVE);
    }

    private void dropSelf(Block block) {
        addDrop(block);
    }
}
