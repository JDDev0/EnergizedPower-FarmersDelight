package me.jddev0.epfd.block;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class EPFDBlocks {
    private EPFDBlocks() {}

    public static final Block ELECTRIC_STOVE = registerBlock("electric_stove",
            new ElectricStoveBlock(AbstractBlock.Settings.create().
                    requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL).
                    luminance(AbstractStoveBlock.LIGHT_EMISSION)));
    public static final Item ELECTRIC_STOVE_ITEM = createBlockItem("electric_stove",
            new ElectricStoveBlock.Item(ELECTRIC_STOVE, new Item.Settings()));

    public static final Block INDUCTION_STOVE = registerBlock("induction_stove",
            new InductionStoveBlock(AbstractBlock.Settings.create().
                    requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL).
                    luminance(AbstractStoveBlock.LIGHT_EMISSION)));
    public static final Item INDUCTION_STOVE_ITEM = createBlockItem("induction_stove",
            new InductionStoveBlock.Item(INDUCTION_STOVE, new Item.Settings()));

    private static <T extends Block> T registerBlock(String name, T block) {
        return Registry.register(Registries.BLOCK, Identifier.of(EnergizedPowerFDMod.MODID, name), block);
    }

    private static Item createBlockItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(EnergizedPowerFDMod.MODID, name), item);
    }

    private static Item createBlockItem(String name, Block block, Item.Settings props) {
        return Registry.register(Registries.ITEM, Identifier.of(EnergizedPowerFDMod.MODID, name),
                new BlockItem(block, props));
    }

    private static Item createBlockItem(String name, Block block) {
        return createBlockItem(name, block, new Item.Settings());
    }

    public static void register() {

    }
}
