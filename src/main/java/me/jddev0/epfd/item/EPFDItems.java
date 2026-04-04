package me.jddev0.epfd.item;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import java.util.function.Function;

public final class EPFDItems {
    private EPFDItems() {}

    public static Item registerItem(String name) {
        return registerItem(name, Item::new, new Item.Properties());
    }

    public static Item registerItem(String name, Item.Properties props) {
        return registerItem(name, Item::new, props);
    }

    public static Item registerItem(String name, Function<Item.Properties, Item> factory) {
        return registerItem(name, factory, new Item.Properties());
    }

    public static  <T extends Item> T registerItem(String name, Function<Item.Properties, T> factory, Item.Properties props) {
        Identifier itemId = Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name);
        return Registry.register(BuiltInRegistries.ITEM, itemId, factory.apply(props.
                setId(ResourceKey.create(Registries.ITEM, itemId))));
    }

    public static void register() {

    }
}
