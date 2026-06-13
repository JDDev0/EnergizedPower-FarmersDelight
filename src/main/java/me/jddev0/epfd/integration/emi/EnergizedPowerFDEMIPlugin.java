package me.jddev0.epfd.integration.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import me.jddev0.epfd.recipe.RichSoilFarmlandCraftingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Optional;

@EmiEntrypoint
public class EnergizedPowerFDEMIPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        registerRecipes(registry);
    }

    private void registerRecipes(EmiRegistry registry) {
        RecipeManager recipeManager = registry.getRecipeManager();

        //Add aether farmland special crafting recipe if loaded
        Optional<RecipeHolder<CraftingRecipe>> recipeOptional = recipeManager.getAllRecipesFor(RecipeType.CRAFTING).stream().
                filter(recipe -> recipe.value() instanceof RichSoilFarmlandCraftingRecipe).findFirst();
        if(recipeOptional.isPresent()) {
            ShapelessRecipe recipe = new ShapelessRecipe("", CraftingBookCategory.MISC, new ItemStack(ModItems.RICH_SOIL_FARMLAND.get()),
                    NonNullList.of(null, new Ingredient[] {
                            Ingredient.of(ItemTags.HOES),
                            Ingredient.of(ModItems.RICH_SOIL.get())
                    }));

            registry.addRecipe(new EmiCraftingRecipe(
                    recipe.getIngredients().stream().map(EmiIngredient::of).toList(),
                    EmiStack.of(recipe.getResultItem(null)),
                    recipeOptional.get().id()
            ));
        }
    }
}
