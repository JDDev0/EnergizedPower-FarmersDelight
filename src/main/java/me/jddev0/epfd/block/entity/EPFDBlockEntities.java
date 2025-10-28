package me.jddev0.epfd.block.entity;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.block.EPFDBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public final class EPFDBlockEntities {
    private EPFDBlockEntities() {}

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EnergizedPowerFDMod.MODID);

    public static final Supplier<BlockEntityType<ElectricStoveBlockEntity>> ELECTRIC_STOVE_ENTITY =
            BLOCK_ENTITIES.register("electric_stove", () -> BlockEntityType.Builder.of(ElectricStoveBlockEntity::new,
                    EPFDBlocks.ELECTRIC_STOVE.get()).build(null));

    public static final Supplier<BlockEntityType<InductionStoveBlockEntity>> INDUCTION_STOVE_ENTITY =
            BLOCK_ENTITIES.register("induction_stove", () -> BlockEntityType.Builder.of(InductionStoveBlockEntity::new,
                    EPFDBlocks.INDUCTION_STOVE.get()).build(null));

    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);
    }
}
