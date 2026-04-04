package me.jddev0.epfd.datagen;

import me.jddev0.ep.datagen.generators.PageContentProvider;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModBookPageContentProvider extends PageContentProvider {
    private static final Style UNIFORM = Style.EMPTY.withFont(new FontDescription.Resource(Identifier.withDefaultNamespace("uniform")));
    private static final Style DEFAULT_FONT = Style.EMPTY.withFont(FontDescription.DEFAULT);

    public ModBookPageContentProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void registerPageContent() {
        {
            Map<Integer, Identifier> changePageIntToId = new HashMap<>();
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
            }, changePageIntToId);
        }
    }

    private String pageId(String chapter, int pageId, String page) {
        return String.format("chapters/%s/%04d_%s", chapter, pageId, page);
    }
}
