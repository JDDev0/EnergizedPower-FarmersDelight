package me.jddev0.epfd.datagen.advancement;

import me.jddev0.ep.api.EPAPI;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModBasicsAdvancements extends FabricAdvancementProvider {
    public ModBasicsAdvancements(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(dataOutput, lookupProvider);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider lookupProvider, Consumer<AdvancementHolder> advancementOutput) {
        AdvancementHolder electric_stove = addAdvancement(
                advancementOutput, EPAPI.id("main/basics/basic_machine_frame"),
                EPFDBlocks.ELECTRIC_STOVE, "electric_stove", AdvancementType.TASK
        );

        AdvancementHolder induction_stove = addAdvancement(
                advancementOutput, EPAPI.id("main/basics/hardened_machine_frame"),
                EPFDBlocks.INDUCTION_STOVE, "induction_stove", AdvancementType.TASK
        );
    }

    private AdvancementHolder addAdvancement(Consumer<AdvancementHolder> advancementOutput, ResourceLocation parent,
                                            ItemLike icon, String advancementId, AdvancementType type) {
        return addAdvancement(advancementOutput, parent, icon, advancementId, type, icon);
    }
    private AdvancementHolder addAdvancement(Consumer<AdvancementHolder> advancementOutput, ResourceLocation parent,
                                            ItemLike icon, String advancementId, AdvancementType type,
                                            ItemLike trigger) {
        return addAdvancement(advancementOutput, parent, new ItemStack(icon), advancementId, type,
                InventoryChangeTrigger.TriggerInstance.hasItems(trigger));
    }
    private AdvancementHolder addAdvancement(Consumer<AdvancementHolder> advancementOutput, ResourceLocation parent,
                                            ItemLike icon, String advancementId, AdvancementType type,
                                            TagKey<Item> trigger) {
        return addAdvancement(advancementOutput, parent, new ItemStack(icon), advancementId, type,
                InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(
                        trigger
                )));
    }
    private AdvancementHolder addAdvancement(Consumer<AdvancementHolder> advancementOutput, ResourceLocation parent,
                                            ItemLike icon, String advancementId, AdvancementType type,
                                            Criterion<?> trigger) {
        return addAdvancement(advancementOutput, parent, new ItemStack(icon), advancementId, type, trigger);
    }
    private AdvancementHolder addAdvancement(Consumer<AdvancementHolder> advancementOutput, ResourceLocation parent,
                                            ItemStack icon, String advancementId, AdvancementType type,
                                            Criterion<?> trigger) {
        return Advancement.Builder.advancement().parent(AdvancementSubProvider.createPlaceholder(parent.toString())).
                display(
                        icon,
                        Component.translatable("advancements.energizedpowerfd." + advancementId + ".title"),
                        Component.translatable("advancements.energizedpowerfd." + advancementId + ".description"),
                        null,
                        type,
                        true,
                        true,
                        false
                ).
                addCriterion("has_the_item", trigger).
                save(advancementOutput, "energizedpowerfd:main/basics/" + advancementId);
    }

    @Override
    public String getName() {
        return "Advancements (Basics)";
    }
}
