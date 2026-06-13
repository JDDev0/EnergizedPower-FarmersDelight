package me.jddev0.epfd.integration.jei;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.recipe.RichSoilFarmlandCraftingRecipe;
import me.shedaniel.rei.plugincompatibilities.api.REIPluginCompatIgnore;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Arrays;
import java.util.Optional;

@JeiPlugin
@REIPluginCompatIgnore
public class EnergizedPowerFDJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        //Add aether farmland special crafting recipe if loaded
        Optional<RecipeHolder<CraftingRecipe>> recipeOptional = recipeManager.getAllRecipesFor(RecipeType.CRAFTING).stream().
                filter(recipe -> recipe.value() instanceof RichSoilFarmlandCraftingRecipe).findFirst();
        if(recipeOptional.isPresent()) {
            ShapelessRecipe recipe = new ShapelessRecipe("", CraftingBookCategory.MISC, new ItemStack(ModItems.RICH_SOIL_FARMLAND.get()),
                    NonNullList.of(null, new Ingredient[] {
                            Ingredient.of(ItemTags.HOES),
                            Ingredient.of(ModItems.RICH_SOIL.get())
                    }));

            registration.addRecipes(RecipeTypes.CRAFTING, Arrays.asList(new RecipeHolder(recipeOptional.get().id(), recipe)));
        }
    }
}
