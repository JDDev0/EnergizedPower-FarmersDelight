package me.jddev0.epfd.block;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.item.EPFDItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class EPFDBlocks {
    private EPFDBlocks() {}

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EnergizedPowerFDMod.MODID);

    private static DeferredItem<Item> createBlockItem(String name, DeferredBlock<Block> blockRegistryObject, Item.Properties props) {
        return EPFDItems.ITEMS.register(name, () -> new BlockItem(blockRegistryObject.get(), props));
    }
    private static DeferredItem<Item> createBlockItem(String name, DeferredBlock<Block> blockRegistryObject) {
        return createBlockItem(name, blockRegistryObject, new Item.Properties());
    }

    public static final DeferredBlock<Block> ELECTRIC_STOVE = BLOCKS.register("electric_stove",
            () -> new ElectricStoveBlock(BlockBehaviour.Properties.of().
                    requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL).
                    lightLevel(AbstractStoveBlock.LIGHT_EMISSION)));
    public static final DeferredItem<Item> ELECTRIC_STOVE_ITEM = EPFDItems.ITEMS.register("electric_stove",
            () -> new ElectricStoveBlock.Item(ELECTRIC_STOVE.get(), new Item.Properties()));

    public static final DeferredBlock<Block> INDUCTION_STOVE = BLOCKS.register("induction_stove",
            () -> new InductionStoveBlock(BlockBehaviour.Properties.of().
                    requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL).
                    lightLevel(AbstractStoveBlock.LIGHT_EMISSION)));
    public static final DeferredItem<Item> INDUCTION_STOVE_ITEM = EPFDItems.ITEMS.register("induction_stove",
            () -> new InductionStoveBlock.Item(INDUCTION_STOVE.get(), new Item.Properties()));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
