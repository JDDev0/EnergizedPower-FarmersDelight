package me.jddev0.epfd.recipe;

import me.jddev0.epfd.EnergizedPowerFDMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class EPFDRecipes {
    private EPFDRecipes() {}

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, EnergizedPowerFDMod.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, EnergizedPowerFDMod.MODID);

    public static final Supplier<RecipeSerializer<RichSoilFarmlandCraftingRecipe>>
            RICH_SOIL_FARMLAND_CRAFTING_RECIPE_SERIALIZER = SERIALIZERS.register("rich_soil_farmland_crafting",
            () -> new SimpleCraftingRecipeSerializer<>(RichSoilFarmlandCraftingRecipe::new));

    public static void register(IEventBus modEventBus) {
        SERIALIZERS.register(modEventBus);
        TYPES.register(modEventBus);
    }
}
