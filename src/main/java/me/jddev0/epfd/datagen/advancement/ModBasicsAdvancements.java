package me.jddev0.epfd.datagen.advancement;

import me.jddev0.ep.api.EPAPI;
import me.jddev0.epfd.block.EPFDBlocks;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
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
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModBasicsAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider lookupProvider, Consumer<Advancement> advancementOutput,
                         ExistingFileHelper existingFileHelper) {
        Advancement electric_stove = addAdvancement(
                advancementOutput, existingFileHelper, EPAPI.id("main/basics/basic_machine_frame"),
                EPFDBlocks.ELECTRIC_STOVE_ITEM, "electric_stove", FrameType.TASK
        );

        Advancement induction_stove = addAdvancement(
                advancementOutput, existingFileHelper, EPAPI.id("main/basics/hardened_machine_frame"),
                EPFDBlocks.INDUCTION_STOVE_ITEM, "induction_stove", FrameType.TASK
        );
    }

    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, ExistingFileHelper existingFileHelper,
                                       ResourceLocation parent, RegistryObject<Item> icon, String advancementId, FrameType type) {
        return addAdvancement(advancementOutput, existingFileHelper, parent, icon.get(), advancementId, type);
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, ExistingFileHelper existingFileHelper,
                                       ResourceLocation parent, ItemLike icon, String advancementId, FrameType type) {
        return addAdvancement(advancementOutput, existingFileHelper, parent, icon, advancementId, type, icon);
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, ExistingFileHelper existingFileHelper,
                                       ResourceLocation parent, RegistryObject<Item> icon, String advancementId, FrameType type,
                                       RegistryObject<Item> trigger) {
        return addAdvancement(advancementOutput, existingFileHelper, parent, icon.get(), advancementId, type, trigger.get());
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, ExistingFileHelper existingFileHelper,
                                       ResourceLocation parent, ItemLike icon, String advancementId, FrameType type,
                                       ItemLike trigger) {
        return addAdvancement(advancementOutput, existingFileHelper, parent, new ItemStack(icon), advancementId, type,
                InventoryChangeTrigger.TriggerInstance.hasItems(trigger));
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, ExistingFileHelper existingFileHelper,
                                       ResourceLocation parent, RegistryObject<Item> icon, String advancementId, FrameType type,
                                       TagKey<Item> trigger) {
        return addAdvancement(advancementOutput, existingFileHelper, parent, icon.get(), advancementId, type, trigger);
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, ExistingFileHelper existingFileHelper,
                                       ResourceLocation parent, ItemLike icon, String advancementId, FrameType type,
                                       TagKey<Item> trigger) {
        return addAdvancement(advancementOutput, existingFileHelper, parent, new ItemStack(icon), advancementId, type,
                InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(
                        trigger
                ).build()));
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, ExistingFileHelper existingFileHelper,
                                       ResourceLocation parent, RegistryObject<Item> icon, String advancementId, FrameType type,
                                       AbstractCriterionTriggerInstance trigger) {
        return addAdvancement(advancementOutput, existingFileHelper, parent, icon.get(), advancementId, type, trigger);
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, ExistingFileHelper existingFileHelper,
                                       ResourceLocation parent, ItemLike icon, String advancementId, FrameType type,
                                       AbstractCriterionTriggerInstance trigger) {
        return addAdvancement(advancementOutput, existingFileHelper, parent, new ItemStack(icon), advancementId, type, trigger);
    }
    private Advancement addAdvancement(Consumer<Advancement> advancementOutput, ExistingFileHelper existingFileHelper,
                                             ResourceLocation parent, ItemStack icon, String advancementId, FrameType type,
                                             AbstractCriterionTriggerInstance trigger) {
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
}
