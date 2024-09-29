package me.jddev0.epfd.screen;

import me.jddev0.epfd.screen.base.ConfigurableRedstoneModeOnlyUpgradableEnergyStorageContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractStoveScreen<T extends AbstractStoveMenu<?>>
        extends ConfigurableRedstoneModeOnlyUpgradableEnergyStorageContainerScreen<T> {
    public AbstractStoveScreen(T menu, Inventory inventory, Component component) {
        super(menu, inventory, component,
                ResourceLocation.fromNamespaceAndPath("energizedpower",
                        "textures/gui/container/generic_energy.png"),
                ResourceLocation.fromNamespaceAndPath("energizedpower",
                        "textures/gui/container/upgrade_view/1_energy_efficiency_1_energy_capacity.png"));

        energyMeterX = 80;
    }
}
