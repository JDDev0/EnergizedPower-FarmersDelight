package me.jddev0.epfd.screen;

import me.jddev0.ep.inventory.UpgradeModuleSlot;
import me.jddev0.ep.inventory.upgrade.UpgradeModuleInventory;
import me.jddev0.ep.machine.configuration.ComparatorMode;
import me.jddev0.ep.machine.configuration.RedstoneMode;
import me.jddev0.ep.machine.upgrade.UpgradeModuleModifier;
import me.jddev0.ep.screen.base.IConfigurableMenu;
import me.jddev0.ep.screen.base.UpgradableEnergyStorageMenu;
import me.jddev0.epfd.block.entity.AbstractStoveBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public abstract class AbstractStoveMenu<T extends AbstractStoveBlockEntity> extends UpgradableEnergyStorageMenu<T>
        implements IConfigurableMenu {
    protected final PropertyDelegate data;

    public AbstractStoveMenu(int id, PlayerInventory inv, PacketByteBuf buf,
                             ScreenHandlerType<?> menuType, Block block) {
        this(
                id, inv.player.getWorld().getBlockEntity(buf.readBlockPos()), inv,
                menuType, block, new UpgradeModuleInventory(
                        UpgradeModuleModifier.ENERGY_CONSUMPTION,
                        UpgradeModuleModifier.ENERGY_CAPACITY
                ), new ArrayPropertyDelegate(1)
        );
    }

    public AbstractStoveMenu(int id, BlockEntity blockEntity, PlayerInventory playerInventory,
                             ScreenHandlerType<?> menuType, Block block,
                             UpgradeModuleInventory upgradeModuleInventory, PropertyDelegate data) {
        super(
                menuType, id,

                playerInventory, blockEntity,
                block,

                upgradeModuleInventory, 2
        );

        checkDataCount(data, 1);
        this.data = data;

        for(int i = 0;i < upgradeModuleInventory.size();i++)
            addSlot(new UpgradeModuleSlot(upgradeModuleInventory, i, 71 + i * 18, 35, this::isInUpgradeModuleView));

        addProperties(this.data);
    }

    @Override
    public RedstoneMode getRedstoneMode() {
        return RedstoneMode.fromIndex(data.get(0));
    }

    @Override
    public ComparatorMode getComparatorMode() {
        return ComparatorMode.ENERGY;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        Slot sourceSlot = slots.get(index);
        if(sourceSlot == null || !sourceSlot.hasStack())
            return ItemStack.EMPTY;

        ItemStack sourceItem = sourceSlot.getStack();
        ItemStack sourceItemCopy = sourceItem.copy();

        if(index < 4 * 9) {
            //Player inventory slot -> Merge into upgrade module inventory
            if(!insertItem(sourceItem, 4 * 9, 4 * 9 + 2, false)) {
                return ItemStack.EMPTY;
            }
        }else if(index < 4 * 9 + 2) {
            //Tile inventory and upgrade module slot -> Merge into player inventory
            if(!insertItem(sourceItem, 0, 4 * 9, false)) {
                return ItemStack.EMPTY;
            }
        }else {
            throw new IllegalArgumentException("Invalid slot index");
        }

        if(sourceItem.getCount() == 0)
            sourceSlot.setStack(ItemStack.EMPTY);
        else
            sourceSlot.markDirty();

        sourceSlot.onTakeItem(player, sourceItem);

        return sourceItemCopy;
    }
}
