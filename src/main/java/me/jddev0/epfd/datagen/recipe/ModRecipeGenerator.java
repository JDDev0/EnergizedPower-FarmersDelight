package me.jddev0.epfd.datagen.recipe;

import me.jddev0.ep.block.EPBlocks;
import me.jddev0.ep.item.EPItems;
import me.jddev0.ep.recipe.OutputItemStackWithPercentages;
import me.jddev0.ep.recipe.PlantGrowthChamberRecipe;
import me.jddev0.ep.recipe.SawmillRecipe;
import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ModRecipeGenerator extends RecipeGenerator {
    private final String FARMERS_DELIGHT_MOD_ID = FarmersDelight.MODID;
    private final String PATH_PREFIX = "compat/" + FARMERS_DELIGHT_MOD_ID + "/";

    public ModRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    @Override
    public void generate() {
        buildCraftingRecipes();
        buildSawmillRecipes();
        buildPlantGrowthChamberRecipes();
    }

    private void buildCraftingRecipes() {
        buildMachineCraftingRecipes();
    }
    private void buildMachineCraftingRecipes() {
        addShapedCraftingRecipe(conditionsFromItem(EPBlocks.BASIC_MACHINE_FRAME_ITEM), Map.of(
                'I', ingredientFromTag(CommonItemTags.PLATES_IRON),
                'R', ingredientFromTag(ConventionalItemTags.REDSTONE_DUSTS),
                'B', Ingredient.ofItems(EPBlocks.BASIC_MACHINE_FRAME_ITEM),
                'S', Ingredient.ofItems(ModBlocks.STOVE.get())
        ), new String[] {
                "ISI",
                "RBR",
                "ISI"
        }, new ItemStack(EPFDBlocks.ELECTRIC_STOVE_ITEM), CraftingRecipeCategory.MISC);

        addShapedCraftingRecipe(conditionsFromItem(EPBlocks.HARDENED_MACHINE_FRAME_ITEM), Map.of(
                'S', ingredientFromTag(CommonItemTags.INGOTS_STEEL),
                'B', Ingredient.ofItems(EPItems.BASIC_CIRCUIT),
                'H', Ingredient.ofItems(EPBlocks.HARDENED_MACHINE_FRAME_ITEM),
                'E', Ingredient.ofItems(EPFDBlocks.ELECTRIC_STOVE_ITEM)
        ), new String[] {
                "SES",
                "BHB",
                "SES"
        }, new ItemStack(EPFDBlocks.INDUCTION_STOVE_ITEM), CraftingRecipeCategory.MISC);
    }

    private void buildSawmillRecipes() {
        addSawmillRecipe(Ingredient.ofItems(ModItems.CUTTING_BOARD.get()), new ItemStack(Items.OAK_PLANKS, 4),
                2, "oak_planks", "cutting_board");

        addSawmillRecipe(ingredientFromTag(ModTags.WOODEN_CABINETS), new ItemStack(Items.STICK, 10),
                3, "sticks", "cabinets");
    }

    private void buildPlantGrowthChamberRecipes() {
        addPlantGrowthChamberRecipe(Ingredient.ofItems(ModItems.TOMATO_SEEDS.get()), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(ModItems.TOMATO_SEEDS.get()), new double[] {
                        1.
                }),
                new OutputItemStackWithPercentages(new ItemStack(ModItems.TOMATO.get()), new double[] {
                        1., .75, .5, .25
                }),
                new OutputItemStackWithPercentages(new ItemStack(ModItems.ROTTEN_TOMATO.get()), new double[] {
                        .05
                })
        }, 16000, "tomatoes", "tomato_seeds");

        addPlantGrowthChamberRecipe(Ingredient.ofItems(ModItems.ONION.get()), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(ModItems.ONION.get()), new double[] {
                        1., .75, .25, .25
                })
        }, 16000, "onions", "onion");

        addPlantGrowthChamberRecipe(Ingredient.ofItems(ModItems.CABBAGE_SEEDS.get()), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(ModItems.CABBAGE_SEEDS.get()), new double[] {
                        1., .33, .33
                }),
                new OutputItemStackWithPercentages(new ItemStack(ModItems.CABBAGE.get()), new double[] {
                        1., .75, .25, .25
                })
        }, 16000, "cabbage", "cabbage_seeds");
    }
    private void addShapedCraftingRecipe(AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category) {
        addShapedCraftingRecipe(hasIngredientTrigger, key, pattern, result, category, "");
    }
    private void addShapedCraftingRecipe(AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category,
                                                String group) {
        addShapedCraftingRecipe(hasIngredientTrigger, key, pattern, result, category, group, "");
    }
    private void addShapedCraftingRecipe(AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category,
                                                String group, String recipeIdSuffix) {
        addShapedCraftingRecipe(hasIngredientTrigger, key, pattern, result, category, group, recipeIdSuffix, "");
    }
    private void addShapedCraftingRecipe(AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category,
                                                String group, String recipeIdSuffix, String recipeIdPrefix) {
        Identifier recipeId = Identifier.of(EnergizedPowerFDMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemPath(result.getItem()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(getKey(recipeId)))
                .criterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(getKey(recipeId)))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        ShapedRecipe recipe = new ShapedRecipe(Objects.requireNonNullElse(group, ""),
                category, RawShapedRecipe.create(key, pattern), result);
        exporter.accept(getKey(recipeId), recipe, advancementBuilder.build(recipeId.withPrefixedPath("recipes/")));
    }
    private void addShapelessCraftingRecipe(AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingRecipeCategory category) {
        addShapelessCraftingRecipe(hasIngredientTrigger, inputs, result, category, "");
    }
    private void addShapelessCraftingRecipe(AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingRecipeCategory category,
                                                   String group) {
        addShapelessCraftingRecipe(hasIngredientTrigger, inputs, result, category, group, "");
    }
    private void addShapelessCraftingRecipe(AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingRecipeCategory category,
                                                   String group, String recipeIdSuffix) {
        addShapelessCraftingRecipe(hasIngredientTrigger, inputs, result, category, group, recipeIdSuffix, "");
    }
    private void addShapelessCraftingRecipe(AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingRecipeCategory category,
                                                   String group, String recipeIdSuffix, String recipeIdPrefix) {
        Identifier recipeId = Identifier.of(EnergizedPowerFDMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemPath(result.getItem()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(getKey(recipeId)))
                .criterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(getKey(recipeId)))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        ShapelessRecipe recipe = new ShapelessRecipe(Objects.requireNonNullElse(group, ""), category, result,
                DefaultedList.copyOf(null, inputs.toArray(Ingredient[]::new)));
        exporter.accept(getKey(recipeId), recipe, advancementBuilder.build(recipeId.withPrefixedPath("recipes/")));
    }
    private void addSawmillRecipe(Ingredient input, ItemStack output,
                                  int sawdustAmount, String outputName, String recipeIngredientName) {
        Identifier recipeId = Identifier.of(EnergizedPowerFDMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillRecipe recipe = new SawmillRecipe(output, input, sawdustAmount);
        exporter.accept(getKey(recipeId), recipe, null);
    }

    private void addPlantGrowthChamberRecipe(Ingredient input, OutputItemStackWithPercentages[] outputs, int ticks,
                                             String outputName, String recipeIngredientName) {
        Identifier recipeId = Identifier.of(EnergizedPowerFDMod.MODID, PATH_PREFIX + "growing/" +
                outputName + "_from_growing_" + recipeIngredientName);

        PlantGrowthChamberRecipe recipe = new PlantGrowthChamberRecipe(outputs, input, ticks);
        exporter.accept(getKey(recipeId), recipe, null);
    }

    private RegistryKey<Recipe<?>> getKey(Identifier recipeId) {
        return RegistryKey.of(RegistryKeys.RECIPE, recipeId);
    }
}
