package me.jddev0.epfd;

import me.jddev0.epfd.config.ModConfigs;
import me.jddev0.epfd.screen.EPFDMenuTypes;
import me.jddev0.epfd.screen.ElectricStoveScreen;
import me.jddev0.epfd.screen.InductionStoveScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class EnergizedPowerFDModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfigs.registerConfigs(false);

        HandledScreens.register(EPFDMenuTypes.ELECTRIC_STOVE_MENU, ElectricStoveScreen::new);
        HandledScreens.register(EPFDMenuTypes.INDUCTION_STOVE_MENU, InductionStoveScreen::new);
    }
}
