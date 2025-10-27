package me.jddev0.epfd.screen;

import me.jddev0.ep.inventory.upgrade.UpgradeModuleInventory;
import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.entity.ElectricStoveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.math.BlockPos;

public class ElectricStoveMenu extends AbstractStoveMenu<ElectricStoveBlockEntity> {
    public ElectricStoveMenu(int id, PlayerInventory inv, BlockPos pos) {
        super(
                id, inv, pos,

                EPFDMenuTypes.ELECTRIC_STOVE_MENU,
                EPFDBlocks.ELECTRIC_STOVE
        );
    }

    public ElectricStoveMenu(int id, BlockEntity blockEntity, PlayerInventory playerInventory, UpgradeModuleInventory upgradeModuleInventory,
                             PropertyDelegate data) {
        super(
                id, blockEntity, playerInventory,

                EPFDMenuTypes.ELECTRIC_STOVE_MENU,

                EPFDBlocks.ELECTRIC_STOVE,

                upgradeModuleInventory, data
        );
    }
}
