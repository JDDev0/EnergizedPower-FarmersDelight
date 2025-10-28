package me.jddev0.epfd.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElectricStoveScreen extends AbstractStoveScreen<ElectricStoveMenu> {
    public ElectricStoveScreen(ElectricStoveMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }
}
