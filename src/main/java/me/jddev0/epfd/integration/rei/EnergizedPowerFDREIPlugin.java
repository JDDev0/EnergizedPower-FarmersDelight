package me.jddev0.epfd.integration.rei;

import me.jddev0.epfd.recipe.RichSoilFarmlandCraftingRecipe;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCraftingDisplay;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Optional;

public class EnergizedPowerFDREIPlugin implements REIClientPlugin {
    @Override
    public String getPluginProviderName() {
        return "EnergizedPower FD";
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        //Add aether farmland special crafting recipe if loaded
        Optional<RecipeHolder<CraftingRecipe>> recipeOptional = registry.getRecipeManager().getAllRecipesFor(RecipeType.CRAFTING).stream().
                filter(recipe -> recipe.value() instanceof RichSoilFarmlandCraftingRecipe).findFirst();
        if(recipeOptional.isPresent()) {
            ShapelessRecipe recipe = new ShapelessRecipe("", CraftingBookCategory.MISC, new ItemStack(ModItems.RICH_SOIL_FARMLAND.get()),
                    NonNullList.of(null, new Ingredient[] {
                            Ingredient.of(ItemTags.HOES),
                            Ingredient.of(ModItems.RICH_SOIL.get())
                    }));

            registry.add(DefaultCraftingDisplay.of(new RecipeHolder(recipeOptional.get().id(), recipe)));
        }
    }
}
