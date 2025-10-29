package me.jddev0.epfd.screen;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public final class EPFDMenuTypes {
    private EPFDMenuTypes() {}

    public static final ScreenHandlerType<ElectricStoveMenu> ELECTRIC_STOVE_MENU = createScreenHandlerType("electric_stove",
            new ExtendedScreenHandlerType<>(ElectricStoveMenu::new));
    public static final ScreenHandlerType<InductionStoveMenu> INDUCTION_STOVE_MENU = createScreenHandlerType("induction_stove",
            new ExtendedScreenHandlerType<>(InductionStoveMenu::new));

    private static <T extends ScreenHandler> ScreenHandlerType<T> createScreenHandlerType(String name, ScreenHandlerType<T> screenHandlerType) {
        return Registry.register(Registries.SCREEN_HANDLER, Identifier.of(EnergizedPowerFDMod.MODID, name), screenHandlerType);
    }

    public static void register() {

    }
}