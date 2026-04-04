package me.jddev0.epfd.screen;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public final class EPFDMenuTypes {
    private EPFDMenuTypes() {}

    public static final MenuType<ElectricStoveMenu> ELECTRIC_STOVE_MENU = createScreenHandlerType("electric_stove",
            new ExtendedMenuType<>(ElectricStoveMenu::new, BlockPos.STREAM_CODEC.cast()));
    public static final MenuType<InductionStoveMenu> INDUCTION_STOVE_MENU = createScreenHandlerType("induction_stove",
            new ExtendedMenuType<>(InductionStoveMenu::new, BlockPos.STREAM_CODEC.cast()));

    private static <T extends AbstractContainerMenu> MenuType<T> createScreenHandlerType(String name, MenuType<T> screenHandlerType) {
        return Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name), screenHandlerType);
    }

    public static void register() {

    }
}