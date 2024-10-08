package me.jddev0.epfd.item;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class EPFDItems {
    private EPFDItems() {}
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EnergizedPowerFDMod.MODID);

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
