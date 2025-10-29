package me.jddev0.epfd.screen;

import me.jddev0.ep.inventory.upgrade.UpgradeModuleInventory;
import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.entity.InductionStoveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;

public class InductionStoveMenu extends AbstractStoveMenu<InductionStoveBlockEntity> {
    public InductionStoveMenu(int id, PlayerInventory inv, PacketByteBuf buf) {
        super(
                id, inv, buf,

                EPFDMenuTypes.INDUCTION_STOVE_MENU,
                EPFDBlocks.INDUCTION_STOVE
        );
    }

    public InductionStoveMenu(int id, BlockEntity blockEntity, PlayerInventory playerInventory, UpgradeModuleInventory upgradeModuleInventory,
                              PropertyDelegate data) {
        super(
                id, blockEntity, playerInventory,

                EPFDMenuTypes.INDUCTION_STOVE_MENU,

                EPFDBlocks.INDUCTION_STOVE,

                upgradeModuleInventory, data
        );
    }
}
