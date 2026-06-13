package me.jddev0.epfd.integration.jei;

import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.recipe.RichSoilFarmlandCraftingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Arrays;
import java.util.Optional;

public class EnergizedPowerFDJEIPlugin implements IModPlugin {
    public static SynchronizedRecipes recipeMap = null;

    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if(recipeMap != null) {
            //Add farmland special crafting recipe if loaded
            Optional<RecipeHolder<CraftingRecipe>> recipeOptional = recipeMap.getAllOfType(RecipeType.CRAFTING).stream().
                    filter(recipe -> recipe.value() instanceof RichSoilFarmlandCraftingRecipe).findFirst();
            if(recipeOptional.isPresent()) {
                ShapelessRecipe recipe = new ShapelessRecipe(new Recipe.CommonInfo(true),
                        new CraftingRecipe.CraftingBookInfo(CraftingBookCategory.MISC, ""), new ItemStackTemplate(ModItems.RICH_SOIL_FARMLAND.get()),
                        NonNullList.of(null, new Ingredient[] {
                                Ingredient.of(Minecraft.getInstance().level.registryAccess().lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.HOES)),
                                Ingredient.of(ModItems.RICH_SOIL.get())
                        }));

                registration.addRecipes(RecipeTypes.CRAFTING, Arrays.asList(new RecipeHolder(recipeOptional.get().id(), recipe)));
            }
        }
    }
}
