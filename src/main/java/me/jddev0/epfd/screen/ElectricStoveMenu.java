package me.jddev0.epfd.screen;

import me.jddev0.ep.inventory.upgrade.UpgradeModuleInventory;
import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.entity.ElectricStoveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ElectricStoveMenu extends AbstractStoveMenu<ElectricStoveBlockEntity> {
    public ElectricStoveMenu(int id, Inventory inv, BlockPos pos) {
        super(
                id, inv, pos,

                EPFDMenuTypes.ELECTRIC_STOVE_MENU,
                EPFDBlocks.ELECTRIC_STOVE
        );
    }

    public ElectricStoveMenu(int id, BlockEntity blockEntity, Inventory playerInventory, UpgradeModuleInventory upgradeModuleInventory,
                             ContainerData data) {
        super(
                id, blockEntity, playerInventory,

                EPFDMenuTypes.ELECTRIC_STOVE_MENU,

                EPFDBlocks.ELECTRIC_STOVE,

                upgradeModuleInventory, data
        );
    }
}
