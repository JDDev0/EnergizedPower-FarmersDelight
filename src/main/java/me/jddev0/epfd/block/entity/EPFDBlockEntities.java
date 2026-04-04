package me.jddev0.epfd.block.entity;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;

import java.util.function.BiFunction;

public final class EPFDBlockEntities {
    private EPFDBlockEntities() {}

    public static final BlockEntityType<ElectricStoveBlockEntity> ELECTRIC_STOVE_ENTITY = registerEnergyStorage(
            createBlockEntity("electric_stove", EPFDBlocks.ELECTRIC_STOVE, ElectricStoveBlockEntity::new),
            (blockEntity, direction) -> blockEntity.limitingEnergyStorage
    );

    public static final BlockEntityType<InductionStoveBlockEntity> INDUCTION_STOVE_ENTITY = registerEnergyStorage(
            createBlockEntity("induction_stove", EPFDBlocks.INDUCTION_STOVE, InductionStoveBlockEntity::new),
            (blockEntity, direction) -> blockEntity.limitingEnergyStorage
    );

    @SuppressWarnings("unchecked")
    private static <T extends BlockEntity> BlockEntityType<T> createBlockEntity(String name, Block block,
                                                                                FabricBlockEntityTypeBuilder.Factory<? extends T> factory) {
        return (BlockEntityType<T>)Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name),
                FabricBlockEntityTypeBuilder.create(factory, block).build(null));
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerEnergyStorage(BlockEntityType<T> blockEntityType,
                                                                                    BiFunction<? super T, Direction, @Nullable EnergyStorage> provider) {
        EnergyStorage.SIDED.registerForBlockEntity(provider, blockEntityType);
        return blockEntityType;
    }

    public static void register() {

    }
}
