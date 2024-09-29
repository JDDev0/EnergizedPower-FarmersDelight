package me.jddev0.epfd.block.entity;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModBlockEntities {
    private ModBlockEntities() {}

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EnergizedPowerFDMod.MODID);

    public static final Supplier<BlockEntityType<ElectricStoveBlockEntity>> ELECTRIC_STOVE_ENTITY =
            BLOCK_ENTITIES.register("electric_stove", () -> BlockEntityType.Builder.of(ElectricStoveBlockEntity::new,
                    ModBlocks.ELECTRIC_STOVE.get()).build(null));

    public static final Supplier<BlockEntityType<InductionStoveBlockEntity>> INDUCTION_STOVE_ENTITY =
            BLOCK_ENTITIES.register("induction_stove", () -> BlockEntityType.Builder.of(InductionStoveBlockEntity::new,
                    ModBlocks.INDUCTION_STOVE.get()).build(null));

    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ELECTRIC_STOVE_ENTITY.get(), ElectricStoveBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                INDUCTION_STOVE_ENTITY.get(), InductionStoveBlockEntity::getEnergyStorageCapability);
    }
}
