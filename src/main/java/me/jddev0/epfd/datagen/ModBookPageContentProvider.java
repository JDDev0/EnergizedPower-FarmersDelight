package me.jddev0.epfd.datagen;

import me.jddev0.ep.datagen.generators.PageContentProvider;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import java.util.concurrent.CompletableFuture;

public class ModBookPageContentProvider extends PageContentProvider {
    private static final Style UNIFORM = Style.EMPTY.withFont(ResourceLocation.withDefaultNamespace("uniform"));
    private static final Style DEFAULT_FONT = Style.EMPTY.withFont(Style.DEFAULT_FONT);

    public ModBookPageContentProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void registerPageContent() {
        addSimplePage(pageId("0060_energy_blocks/0050_energy_consumption", 105, "electric_stoves"), Component.empty().append(
                Component.translatable("book.energizedpowerfd.page.electric_stoves.1").
                        withStyle(ChatFormatting.BLACK, ChatFormatting.BOLD).withStyle(UNIFORM)
        ).append(
                Component.literal("\n• ").
                        withStyle(ChatFormatting.BLACK, ChatFormatting.BOLD).withStyle(UNIFORM).
                        append(Component.translatable("book.energizedpowerfd.page.electric_stoves.2").
                                withStyle(ChatFormatting.BLACK, ChatFormatting.BOLD).withStyle(UNIFORM))
        ).append(
                Component.literal("\n• ").
                        withStyle(ChatFormatting.BLACK, ChatFormatting.BOLD).withStyle(UNIFORM).
                        append(Component.translatable("book.energizedpowerfd.page.electric_stoves.3").
                                withStyle(ChatFormatting.BLACK, ChatFormatting.BOLD).withStyle(UNIFORM))
        ), new Block[] {
                EPFDBlocks.ELECTRIC_STOVE,
                EPFDBlocks.INDUCTION_STOVE
        });
    }

    private String pageId(String chapter, int pageId, String page) {
        return String.format("chapters/%s/%04d_%s", chapter, pageId, page);
    }
}
