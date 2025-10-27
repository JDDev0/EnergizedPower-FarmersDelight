package me.jddev0.epfd.block.entity;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.block.EPFDBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
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
                                                                                BlockEntityType.BlockEntityFactory<? extends T> factory) {
        return (BlockEntityType<T>)Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(EnergizedPowerFDMod.MODID, name),
                BlockEntityType.Builder.create(factory, block).build(null));
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerEnergyStorage(BlockEntityType<T> blockEntityType,
                                                                                    BiFunction<? super T, Direction, @Nullable EnergyStorage> provider) {
        EnergyStorage.SIDED.registerForBlockEntity(provider, blockEntityType);
        return blockEntityType;
    }

    public static void register() {

    }
}
