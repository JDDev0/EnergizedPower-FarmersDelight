package me.jddev0.epfd.datagen.advancement;

import me.jddev0.ep.api.EPAPI;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.advancement.AdvancementTabGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModBasicsAdvancements extends FabricAdvancementProvider {
    public ModBasicsAdvancements(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> advancementOutput) {
        Advancement electric_stove = addAdvancement(
                advancementOutput, EPAPI.id("main/basics/basic_machine_frame"),
                EPFDBlocks.ELECTRIC_STOVE, "electric_stove", AdvancementFrame.TASK
        );

        Advancement induction_stove = addAdvancement(
                advancementOutput, EPAPI.id("main/basics/hardened_machine_frame"),
                EPFDBlocks.INDUCTION_STOVE, "induction_stove", AdvancementFrame.TASK
        );
    }

    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, Identifier parent,
                                       ItemConvertible icon, String advancementId, AdvancementFrame type) {
        return addAdvancement(advancementOutput, parent, icon, advancementId, type, icon);
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, Identifier parent,
                                       ItemConvertible icon, String advancementId, AdvancementFrame type,
                                       ItemConvertible trigger) {
        return addAdvancement(advancementOutput, parent, new ItemStack(icon), advancementId, type,
                InventoryChangedCriterion.Conditions.items(trigger));
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, Identifier parent,
                                       ItemConvertible icon, String advancementId, AdvancementFrame type,
                                       TagKey<Item> trigger) {
        return addAdvancement(advancementOutput, parent, new ItemStack(icon), advancementId, type,
                InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().tag(
                        trigger
                ).build()));
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, Identifier parent,
                                            ItemStack icon, String advancementId, AdvancementFrame type,
                                       AbstractCriterionConditions trigger) {
        return Advancement.Builder.create().parent(AdvancementTabGenerator.createEmptyAdvancement(parent.toString())).
                display(
                        icon,
                        Text.translatable("advancements.energizedpowerfd." + advancementId + ".title"),
                        Text.translatable("advancements.energizedpowerfd." + advancementId + ".description"),
                        null,
                        type,
                        true,
                        true,
                        false
                ).
                criterion("has_the_item", trigger).
                build(advancementOutput, "energizedpowerfd:main/basics/" + advancementId);
    }

    @Override
    public String getName() {
        return "Advancements (Basics)";
    }
}
