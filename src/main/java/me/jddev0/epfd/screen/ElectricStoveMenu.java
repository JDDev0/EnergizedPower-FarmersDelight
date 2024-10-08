package me.jddev0.epfd.screen;

import me.jddev0.ep.inventory.upgrade.UpgradeModuleInventory;
import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.entity.ElectricStoveBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ElectricStoveMenu extends AbstractStoveMenu<ElectricStoveBlockEntity> {
    public ElectricStoveMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
        super(
                id, inv, buffer,

                EPFDMenuTypes.ELECTRIC_STOVE_MENU.get(),
                EPFDBlocks.ELECTRIC_STOVE.get()
        );
    }

    public ElectricStoveMenu(int id, Inventory inv, BlockEntity blockEntity, UpgradeModuleInventory upgradeModuleInventory,
                             ContainerData data) {
        super(
                id, inv, blockEntity,

                EPFDMenuTypes.ELECTRIC_STOVE_MENU.get(),

                EPFDBlocks.ELECTRIC_STOVE.get(),

                upgradeModuleInventory, data
        );
    }
}
