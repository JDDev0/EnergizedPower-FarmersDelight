package me.jddev0.epfd.block;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.item.EPFDItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class EPFDBlocks {
    private EPFDBlocks() {}

    public static Block registerBlock(String name, AbstractBlock.Settings props) {
        return registerBlock(name, Block::new, props);
    }

    public static <T extends Block> T registerBlock(String name, Function<AbstractBlock.Settings, T> factory, AbstractBlock.Settings props) {
        Identifier blockId = Identifier.of(EnergizedPowerFDMod.MODID, name);
        return Registry.register(Registries.BLOCK, blockId, factory.apply(props.
                registryKey(RegistryKey.of(RegistryKeys.BLOCK, blockId))));
    }

    public static Item createBlockItem(String name, Block block) {
        return createBlockItem(name, BlockItem::new, block, new Item.Settings());
    }

    public static Item createBlockItem(String name, BiFunction<Block, Item.Settings, Item> factory, Block block) {
        return createBlockItem(name, props -> factory.apply(block, props), new Item.Settings());
    }

    public static Item createBlockItem(String name, BiFunction<Block, Item.Settings, Item> factory, Block block, Item.Settings props) {
        return createBlockItem(name, p -> factory.apply(block, p), props);
    }

    public static Item createBlockItem(String name, Function<Item.Settings, Item> factory) {
        return createBlockItem(name, factory, new Item.Settings());
    }

    public static Item createBlockItem(String name, Function<Item.Settings, Item> factory, Item.Settings props) {
        return EPFDItems.registerItem(name, factory, props);
    }

    public static final Block ELECTRIC_STOVE = registerBlock("electric_stove",
            ElectricStoveBlock::new, AbstractBlock.Settings.create().requiresTool().
                    strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL).
                    luminance(AbstractStoveBlock.LIGHT_EMISSION));
    public static final Item ELECTRIC_STOVE_ITEM = createBlockItem("electric_stove",
            ElectricStoveBlock.Item::new, ELECTRIC_STOVE);

    public static final Block INDUCTION_STOVE = registerBlock("induction_stove",
            InductionStoveBlock::new, AbstractBlock.Settings.create().requiresTool().
                    strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL).
                    luminance(AbstractStoveBlock.LIGHT_EMISSION));
    public static final Item INDUCTION_STOVE_ITEM = createBlockItem("induction_stove",
            InductionStoveBlock.Item::new, INDUCTION_STOVE);

    public static void register() {

    }
}
