package me.jddev0.epfd.datagen;

import me.jddev0.epfd.datagen.loot.ModBlockLootTables;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EnergizedPowerFDDataGenerators implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModBookPageContentProvider::new);

        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModBlockLootTables::new);
        ModAdvancementProvider.create(pack);

        pack.addProvider(ModBlockTagProvider::new);
    }
}
