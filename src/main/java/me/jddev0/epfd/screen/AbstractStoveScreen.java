package me.jddev0.epfd.screen;

import me.jddev0.epfd.screen.base.ConfigurableRedstoneModeOnlyUpgradableEnergyStorageContainerScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public abstract class AbstractStoveScreen<T extends AbstractStoveMenu<?>>
        extends ConfigurableRedstoneModeOnlyUpgradableEnergyStorageContainerScreen<T> {
    public AbstractStoveScreen(T menu, PlayerInventory inventory, Text component) {
        super(menu, inventory, component,
                Identifier.of("energizedpower",
                        "textures/gui/container/generic_energy.png"),
                Identifier.of("energizedpower",
                        "textures/gui/container/upgrade_view/1_energy_efficiency_1_energy_capacity.png"));

        energyMeterX = 80;
    }
}
