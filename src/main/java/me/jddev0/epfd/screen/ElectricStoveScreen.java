package me.jddev0.epfd.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ElectricStoveScreen extends AbstractStoveScreen<ElectricStoveMenu> {
    public ElectricStoveScreen(ElectricStoveMenu menu, PlayerInventory inventory, Text component) {
        super(menu, inventory, component);
    }
}
