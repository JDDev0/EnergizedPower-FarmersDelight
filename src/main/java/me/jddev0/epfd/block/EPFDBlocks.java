package me.jddev0.epfd.block;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.item.EPFDItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class EPFDBlocks {
    private EPFDBlocks() {}

    public static Block registerBlock(String name, BlockBehaviour.Properties props) {
        return registerBlock(name, Block::new, props);
    }

    public static <T extends Block> T registerBlock(String name, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties props) {
        Identifier blockId = Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name);
        return Registry.register(BuiltInRegistries.BLOCK, blockId, factory.apply(props.
                setId(ResourceKey.create(Registries.BLOCK, blockId))));
    }

    public static Item createBlockItem(String name, Block block) {
        return createBlockItem(name, BlockItem::new, block, new Item.Properties());
    }

    public static Item createBlockItem(String name, BiFunction<Block, Item.Properties, Item> factory, Block block) {
        return createBlockItem(name, props -> factory.apply(block, props), new Item.Properties());
    }

    public static Item createBlockItem(String name, BiFunction<Block, Item.Properties, Item> factory, Block block, Item.Properties props) {
        return createBlockItem(name, p -> factory.apply(block, p), props);
    }

    public static Item createBlockItem(String name, Function<Item.Properties, Item> factory) {
        return createBlockItem(name, factory, new Item.Properties());
    }

    public static Item createBlockItem(String name, Function<Item.Properties, Item> factory, Item.Properties props) {
        return EPFDItems.registerItem(name, factory, props);
    }

    public static final Block ELECTRIC_STOVE = registerBlock("electric_stove",
            ElectricStoveBlock::new, BlockBehaviour.Properties.of().requiresCorrectToolForDrops().
                    strength(5.0f, 6.0f).sound(SoundType.METAL).
                    lightLevel(AbstractStoveBlock.LIGHT_EMISSION));
    public static final Item ELECTRIC_STOVE_ITEM = createBlockItem("electric_stove",
            ElectricStoveBlock.Item::new, ELECTRIC_STOVE);

    public static final Block INDUCTION_STOVE = registerBlock("induction_stove",
            InductionStoveBlock::new, BlockBehaviour.Properties.of().requiresCorrectToolForDrops().
                    strength(5.0f, 6.0f).sound(SoundType.METAL).
                    lightLevel(AbstractStoveBlock.LIGHT_EMISSION));
    public static final Item INDUCTION_STOVE_ITEM = createBlockItem("induction_stove",
            InductionStoveBlock.Item::new, INDUCTION_STOVE);

    public static void register() {

    }
}
