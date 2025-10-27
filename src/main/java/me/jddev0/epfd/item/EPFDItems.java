package me.jddev0.epfd.item;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class EPFDItems {
    private EPFDItems() {}

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(EnergizedPowerFDMod.MODID, name), item);
    }

    public static void register() {

    }
}
