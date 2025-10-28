package me.jddev0.epfd.screen;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class EPFDMenuTypes {
    private EPFDMenuTypes() {}

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EnergizedPowerFDMod.MODID);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static final Supplier<MenuType<ElectricStoveMenu>> ELECTRIC_STOVE_MENU = registerMenuType("electric_stove",
            ElectricStoveMenu::new);
    public static final Supplier<MenuType<InductionStoveMenu>> INDUCTION_STOVE_MENU = registerMenuType("induction_stove",
            InductionStoveMenu::new);

    public static void register(IEventBus modEventBus) {
        MENUS.register(modEventBus);
    }
}