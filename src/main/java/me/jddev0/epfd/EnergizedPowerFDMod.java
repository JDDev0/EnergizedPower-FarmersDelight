package me.jddev0.epfd;

import com.mojang.logging.LogUtils;
import me.jddev0.ep.item.CreativeTabEntriesHelper;
import me.jddev0.ep.item.EPCreativeModeTab;
import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.entity.EPFDBlockEntities;
import me.jddev0.epfd.config.ModConfigs;
import me.jddev0.epfd.item.EPFDItems;
import me.jddev0.epfd.screen.EPFDMenuTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import org.slf4j.Logger;

import java.util.function.Consumer;

public class EnergizedPowerFDMod implements ModInitializer {
    public static final String MODID = "energizedpowerfd";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        ModConfigs.registerConfigs(true);

        EPFDItems.register();
        EPFDBlocks.register();
        EPFDBlockEntities.register();
        EPFDMenuTypes.register();

        addCreativeTab();
    }

    private void addCreativeTab() {
        addCreativeTabFor(EPCreativeModeTab.ENERGIZED_POWER_TAB_REG_KEY, event -> {
            event.accept(EPFDBlocks.ELECTRIC_STOVE_ITEM);
            event.accept(EPFDBlocks.INDUCTION_STOVE_ITEM);
        });
    }

    private void addCreativeTabFor(RegistryKey<ItemGroup> groupKey,
                                   Consumer<CreativeTabEntriesHelper> consumer) {
        ItemGroupEvents.modifyEntriesEvent(groupKey).
                register(entries -> consumer.accept(new CreativeTabEntriesHelper(entries)));
    }
}
