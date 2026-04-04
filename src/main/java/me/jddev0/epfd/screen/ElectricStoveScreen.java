package me.jddev0.epfd.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class ElectricStoveScreen extends AbstractStoveScreen<ElectricStoveMenu> {
    public ElectricStoveScreen(ElectricStoveMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }
}
