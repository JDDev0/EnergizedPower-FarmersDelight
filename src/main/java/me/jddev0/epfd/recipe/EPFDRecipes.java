package me.jddev0.epfd.recipe;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public final class EPFDRecipes {
    private EPFDRecipes() {}

    public static final RecipeSerializer<RichSoilFarmlandCraftingRecipe>
            RICH_SOIL_FARMLAND_CRAFTING_RECIPE_SERIALIZER = createSerializer("rich_soil_farmland_crafting",
            RichSoilFarmlandCraftingRecipe.SERIALIZER);

    private static <T extends Recipe<?>> RecipeSerializer<T> createSerializer(String name, RecipeSerializer<T> instance) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name), instance);
    }
    private static <T extends Recipe<?>> RecipeType<T> createRecipeType(String name, RecipeType<T> instance) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, name), instance);
    }
    public static void register() {

    }
}
