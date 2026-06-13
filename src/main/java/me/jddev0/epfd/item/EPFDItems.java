package me.jddev0.epfd.item;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class EPFDItems {
    private EPFDItems() {}

    public static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name), item);
    }

    public static void register() {

    }
}
