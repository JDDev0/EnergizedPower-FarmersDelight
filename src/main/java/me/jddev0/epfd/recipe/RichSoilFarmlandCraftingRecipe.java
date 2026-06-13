package me.jddev0.epfd.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.registry.ModItems;

public class RichSoilFarmlandCraftingRecipe extends CustomRecipe {
    @Override
    public boolean matches(CraftingInput container, Level level) {
        int dirtCount = 0;
        int hoeCount = 0;

        for(int i = 0;i < container.size();i++) {
            ItemStack itemStack = container.getItem(i);
            if(!itemStack.isEmpty()) {
                if(itemStack.is(ModItems.RICH_SOIL.get().asItem())) {
                    dirtCount++;
                }else if(itemStack.is(ItemTags.HOES)) {
                    hoeCount++;
                }else {
                    return false;
                }
            }
        }

        return dirtCount == 1 && hoeCount == 1;
    }

    @Override
    public ItemStack assemble(CraftingInput container) {
        return new ItemStack(ModItems.RICH_SOIL_FARMLAND.get());
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput container) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(container.size(), ItemStack.EMPTY);

        for(int i = 0; i < remainders.size(); ++i) {
            ItemStack itemStack = container.getItem(i);
            if(!itemStack.isEmpty()) {
                if(itemStack.is(ItemTags.HOES)) {
                    ItemStack copy = itemStack.copy();

                    //TODO fix for durability enchantment -> Get ServerWorld somehow and use instead of if:
                    //     "copy.hurtAndBreak(1, null, null, item -> copy.setCount(0));"
                    if(copy.isDamageableItem()) {
                        int d = copy.getDamageValue() + 1;
                        copy.setDamageValue(d);
                        if(d >= copy.getMaxDamage())
                            copy.setCount(0);
                    }

                    if(!copy.isEmpty())
                        remainders.set(i, copy);
                }
            }
        }

        return remainders;
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return SERIALIZER;
    }

    private static final MapCodec<RichSoilFarmlandCraftingRecipe> CODEC = MapCodec.unit(new RichSoilFarmlandCraftingRecipe());
    private static final StreamCodec<RegistryFriendlyByteBuf, RichSoilFarmlandCraftingRecipe> STREAM_CODEC = StreamCodec.of(
            (buffer, recipe) -> {},
            buffer -> new RichSoilFarmlandCraftingRecipe()
    );
    public static final RecipeSerializer<RichSoilFarmlandCraftingRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);
}
