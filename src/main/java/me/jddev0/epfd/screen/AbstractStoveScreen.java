package me.jddev0.epfd.screen;

import me.jddev0.epfd.screen.base.ConfigurableRedstoneModeOnlyUpgradableEnergyStorageContainerScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public abstract class AbstractStoveScreen<T extends AbstractStoveMenu<?>>
        extends ConfigurableRedstoneModeOnlyUpgradableEnergyStorageContainerScreen<T> {
    public AbstractStoveScreen(T menu, Inventory inventory, Component component) {
        super(menu, inventory, component,
                Identifier.fromNamespaceAndPath("energizedpower",
                        "textures/gui/container/generic_energy.png"),
                Identifier.fromNamespaceAndPath("energizedpower",
                        "textures/gui/container/upgrade_view/1_energy_efficiency_1_energy_capacity.png"));

        energyMeterX = 80;
    }
}
