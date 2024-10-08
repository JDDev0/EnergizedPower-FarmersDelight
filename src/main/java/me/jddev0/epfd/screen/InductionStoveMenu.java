package me.jddev0.epfd.screen;

import me.jddev0.ep.inventory.upgrade.UpgradeModuleInventory;
import me.jddev0.epfd.block.EPFDBlocks;
import me.jddev0.epfd.block.entity.InductionStoveBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class InductionStoveMenu extends AbstractStoveMenu<InductionStoveBlockEntity> {
    public InductionStoveMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
        super(
                id, inv, buffer,

                EPFDMenuTypes.INDUCTION_STOVE_MENU.get(),
                EPFDBlocks.INDUCTION_STOVE.get()
        );
    }

    public InductionStoveMenu(int id, Inventory inv, BlockEntity blockEntity, UpgradeModuleInventory upgradeModuleInventory,
                             ContainerData data) {
        super(
                id, inv, blockEntity,

                EPFDMenuTypes.INDUCTION_STOVE_MENU.get(),

                EPFDBlocks.INDUCTION_STOVE.get(),

                upgradeModuleInventory, data
        );
    }
}
