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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

@Mod(EnergizedPowerFDMod.MODID)
public class EnergizedPowerFDMod {
    public static final String MODID = "energizedpowerfd";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EnergizedPowerFDMod(IEventBus modEventBus) {
        ModConfigs.registerConfigs(true);

        EPFDItems.register(modEventBus);
        EPFDBlocks.register(modEventBus);
        EPFDBlockEntities.register(modEventBus);
        EPFDMenuTypes.register(modEventBus);

        modEventBus.addListener(this::addCreativeTab);
        modEventBus.addListener(this::registerCapabilities);
    }

    private void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == EPCreativeModeTab.ENERGIZED_POWER_TAB.get()) {
            event.accept(EPFDBlocks.ELECTRIC_STOVE_ITEM);
            event.accept(EPFDBlocks.INDUCTION_STOVE_ITEM);
        }
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        //Block Entities
        EPFDBlockEntities.registerCapabilities(event);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModConfigs.registerConfigs(false);
        }
        
        
        @SubscribeEvent
        public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
            event.register(EPFDMenuTypes.ELECTRIC_STOVE_MENU.get(), ElectricStoveScreen::new);
            event.register(EPFDMenuTypes.INDUCTION_STOVE_MENU.get(), InductionStoveScreen::new);
        }
    }
}
