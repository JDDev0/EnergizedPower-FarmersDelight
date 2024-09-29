package me.jddev0.epfd;

import com.mojang.logging.LogUtils;
import me.jddev0.ep.item.ModCreativeModeTab;
import me.jddev0.epfd.block.ModBlocks;
import me.jddev0.epfd.block.entity.ModBlockEntities;
import me.jddev0.epfd.config.ModConfigs;
import me.jddev0.epfd.item.ModItems;
import me.jddev0.epfd.screen.ElectricStoveScreen;
import me.jddev0.epfd.screen.InductionStoveScreen;
import me.jddev0.epfd.screen.ModMenuTypes;
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

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::addCreativeTab);
        modEventBus.addListener(this::registerCapabilities);
    }

    private void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == ModCreativeModeTab.ENERGIZED_POWER_TAB.get()) {
            event.accept(ModBlocks.ELECTRIC_STOVE_ITEM);
            event.accept(ModBlocks.INDUCTION_STOVE_ITEM);
        }
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        //Block Entities
        ModBlockEntities.registerCapabilities(event);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModConfigs.registerConfigs(false);
        }
        
        
        @SubscribeEvent
        public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.ELECTRIC_STOVE_MENU.get(), ElectricStoveScreen::new);
            event.register(ModMenuTypes.INDUCTION_STOVE_MENU.get(), InductionStoveScreen::new);
        }
    }
}
