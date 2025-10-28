package me.jddev0.epfd;

import com.mojang.logging.LogUtils;
import me.jddev0.ep.item.EPCreativeModeTab;
import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.entity.EPFDBlockEntities;
import me.jddev0.epfd.config.ModConfigs;
import me.jddev0.epfd.item.EPFDItems;
import me.jddev0.epfd.screen.ElectricStoveScreen;
import me.jddev0.epfd.screen.InductionStoveScreen;
import me.jddev0.epfd.screen.EPFDMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(EnergizedPowerFDMod.MODID)
public class EnergizedPowerFDMod {
    public static final String MODID = "energizedpowerfd";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EnergizedPowerFDMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModConfigs.registerConfigs(true);

        EPFDItems.register(modEventBus);
        EPFDBlocks.register(modEventBus);
        EPFDBlockEntities.register(modEventBus);
        EPFDMenuTypes.register(modEventBus);

        modEventBus.addListener(this::addCreativeTab);
    }

    private void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == EPCreativeModeTab.ENERGIZED_POWER_TAB.get()) {
            event.accept(EPFDBlocks.ELECTRIC_STOVE_ITEM);
            event.accept(EPFDBlocks.INDUCTION_STOVE_ITEM);
        }
    }

    @Mod.EventBusSubscriber(modid = EnergizedPowerFDMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModConfigs.registerConfigs(false);

            MenuScreens.register(EPFDMenuTypes.ELECTRIC_STOVE_MENU.get(), ElectricStoveScreen::new);
            MenuScreens.register(EPFDMenuTypes.INDUCTION_STOVE_MENU.get(), InductionStoveScreen::new);
        }
    }
}
