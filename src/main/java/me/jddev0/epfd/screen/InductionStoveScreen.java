package me.jddev0.epfd.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InductionStoveScreen extends AbstractStoveScreen<InductionStoveMenu> {
    public InductionStoveScreen(InductionStoveMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }
}
