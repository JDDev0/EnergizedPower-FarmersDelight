package me.jddev0.epfd.datagen;

import me.jddev0.ep.datagen.generators.PageContentProvider;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Style;
import net.minecraft.text.StyleSpriteSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModBookPageContentProvider extends PageContentProvider {
    private static final Style UNIFORM = Style.EMPTY.withFont(new StyleSpriteSource.Font(Identifier.ofVanilla("uniform")));
    private static final Style DEFAULT_FONT = Style.EMPTY.withFont(StyleSpriteSource.DEFAULT);

    public ModBookPageContentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void registerPageContent() {
        {
            Map<Integer, Identifier> changePageIntToId = new HashMap<>();
            addSimplePage(pageId("0060_energy_blocks/0050_energy_consumption", 105, "electric_stoves"), Text.empty().append(
                    Text.translatable("book.energizedpowerfd.page.electric_stoves.1").
                            formatted(Formatting.BLACK, Formatting.BOLD).fillStyle(UNIFORM)
            ).append(
                    Text.literal("\n• ").
                            formatted(Formatting.BLACK, Formatting.BOLD).fillStyle(UNIFORM).
                            append(Text.translatable("book.energizedpowerfd.page.electric_stoves.2").
                                    formatted(Formatting.BLACK, Formatting.BOLD).fillStyle(UNIFORM))
            ).append(
                    Text.literal("\n• ").
                            formatted(Formatting.BLACK, Formatting.BOLD).fillStyle(UNIFORM).
                            append(Text.translatable("book.energizedpowerfd.page.electric_stoves.3").
                                    formatted(Formatting.BLACK, Formatting.BOLD).fillStyle(UNIFORM))
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
