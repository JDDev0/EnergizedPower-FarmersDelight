package me.jddev0.epfd.datagen;

import me.jddev0.ep.datagen.generators.PageContentProvider;
import me.jddev0.epfd.block.EPFDBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBookPageContentProvider extends PageContentProvider {
    private static final Style UNIFORM = Style.EMPTY.withFont(new ResourceLocation("uniform"));
    private static final Style DEFAULT_FONT = Style.EMPTY.withFont(Style.DEFAULT_FONT);

    public ModBookPageContentProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "energizedpowerfd", existingFileHelper);
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
                EPFDBlocks.ELECTRIC_STOVE.get(),
                EPFDBlocks.INDUCTION_STOVE.get()
        });
    }

    private String pageId(String chapter, int pageId, String page) {
        return String.format("chapters/%s/%04d_%s", chapter, pageId, page);
    }
}
