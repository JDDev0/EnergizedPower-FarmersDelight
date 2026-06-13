package me.jddev0.epfd.block;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class EPFDBlocks {
    private EPFDBlocks() {}

    public static final Block ELECTRIC_STOVE = registerBlock("electric_stove",
            new ElectricStoveBlock(BlockBehaviour.Properties.of().
                    requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL).
                    lightLevel(AbstractStoveBlock.LIGHT_EMISSION)));
    public static final Item ELECTRIC_STOVE_ITEM = createBlockItem("electric_stove",
            new ElectricStoveBlock.Item(ELECTRIC_STOVE, new Item.Properties()));

    public static final Block INDUCTION_STOVE = registerBlock("induction_stove",
            new InductionStoveBlock(BlockBehaviour.Properties.of().
                    requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL).
                    lightLevel(AbstractStoveBlock.LIGHT_EMISSION)));
    public static final Item INDUCTION_STOVE_ITEM = createBlockItem("induction_stove",
            new InductionStoveBlock.Item(INDUCTION_STOVE, new Item.Properties()));

    private static <T extends Block> T registerBlock(String name, T block) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name), block);
    }

    private static Item createBlockItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name), item);
    }

    private static Item createBlockItem(String name, Block block, Item.Properties props) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name),
                new BlockItem(block, props));
    }

    private static Item createBlockItem(String name, Block block) {
        return createBlockItem(name, block, new Item.Properties());
    }

    public static void register() {

    }
}
