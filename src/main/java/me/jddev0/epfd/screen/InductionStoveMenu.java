package me.jddev0.epfd.screen;

import me.jddev0.ep.inventory.upgrade.UpgradeModuleInventory;
import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.entity.InductionStoveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class InductionStoveMenu extends AbstractStoveMenu<InductionStoveBlockEntity> {
    public InductionStoveMenu(int id, Inventory inv, BlockPos pos) {
        super(
                id, inv, pos,

                EPFDMenuTypes.INDUCTION_STOVE_MENU,
                EPFDBlocks.INDUCTION_STOVE
        );
    }

    public InductionStoveMenu(int id, BlockEntity blockEntity, Inventory playerInventory, UpgradeModuleInventory upgradeModuleInventory,
                              ContainerData data) {
        super(
                id, blockEntity, playerInventory,

                EPFDMenuTypes.INDUCTION_STOVE_MENU,

                EPFDBlocks.INDUCTION_STOVE,

                upgradeModuleInventory, data
        );
    }
}
