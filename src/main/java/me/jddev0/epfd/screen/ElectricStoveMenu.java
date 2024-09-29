package me.jddev0.epfd.screen;

import me.jddev0.ep.inventory.upgrade.UpgradeModuleInventory;
import me.jddev0.epfd.block.ModBlocks;
import me.jddev0.epfd.block.entity.ElectricStoveBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ElectricStoveMenu extends AbstractStoveMenu<ElectricStoveBlockEntity> {
    public ElectricStoveMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
        super(
                id, inv, buffer,

                ModMenuTypes.ELECTRIC_STOVE_MENU.get(),
                ModBlocks.ELECTRIC_STOVE.get()
        );
    }

    public ElectricStoveMenu(int id, Inventory inv, BlockEntity blockEntity, UpgradeModuleInventory upgradeModuleInventory,
                             ContainerData data) {
        super(
                id, inv, blockEntity,

                ModMenuTypes.ELECTRIC_STOVE_MENU.get(),

                ModBlocks.ELECTRIC_STOVE.get(),

                upgradeModuleInventory, data
        );
    }
}
