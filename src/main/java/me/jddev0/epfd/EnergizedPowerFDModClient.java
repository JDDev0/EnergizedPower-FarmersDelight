package me.jddev0.epfd;

import me.jddev0.epfd.config.ModConfigs;
import me.jddev0.epfd.screen.EPFDMenuTypes;
import me.jddev0.epfd.screen.ElectricStoveScreen;
import me.jddev0.epfd.screen.InductionStoveScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class EnergizedPowerFDModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfigs.registerConfigs(false);

        MenuScreens.register(EPFDMenuTypes.ELECTRIC_STOVE_MENU, ElectricStoveScreen::new);
        MenuScreens.register(EPFDMenuTypes.INDUCTION_STOVE_MENU, InductionStoveScreen::new);
    }
}
