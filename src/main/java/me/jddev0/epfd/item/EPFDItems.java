package me.jddev0.epfd.item;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public final class EPFDItems {
    private EPFDItems() {}

    public static Item registerItem(String name) {
        return registerItem(name, Item::new, new Item.Settings());
    }

    public static Item registerItem(String name, Item.Settings props) {
        return registerItem(name, Item::new, props);
    }

    public static Item registerItem(String name, Function<Item.Settings, Item> factory) {
        return registerItem(name, factory, new Item.Settings());
    }

    public static  <T extends Item> T registerItem(String name, Function<Item.Settings, T> factory, Item.Settings props) {
        Identifier itemId = Identifier.of(EnergizedPowerFDMod.MODID, name);
        return Registry.register(Registries.ITEM, itemId, factory.apply(props.
                registryKey(RegistryKey.of(RegistryKeys.ITEM, itemId))));
    }

    public static void register() {

    }
}
