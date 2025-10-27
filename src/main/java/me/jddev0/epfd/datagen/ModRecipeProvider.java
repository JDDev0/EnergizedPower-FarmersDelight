package me.jddev0.epfd.datagen;

import me.jddev0.ep.block.EPBlocks;
import me.jddev0.ep.item.EPItems;
import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.ep.recipe.*;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
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
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final String FARMERS_DELIGHT_MOD_ID = FarmersDelight.MODID;
    private static final String PATH_PREFIX = "compat/" + FARMERS_DELIGHT_MOD_ID + "/";

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    public void generate(RecipeExporter output) {
        buildCraftingRecipes(output);
        buildSawmillRecipes(output);
        buildPlantGrowthChamberRecipes(output);
    }

    private void buildCraftingRecipes(RecipeExporter output) {
        buildMachineCraftingRecipes(output);
    }
    private void buildMachineCraftingRecipes(RecipeExporter output) {
        addShapedCraftingRecipe(output, conditionsFromItem(EPBlocks.BASIC_MACHINE_FRAME_ITEM), Map.of(
                'I', Ingredient.fromTag(CommonItemTags.PLATES_IRON),
                'R', Ingredient.fromTag(ConventionalItemTags.REDSTONE_DUSTS),
                'B', Ingredient.ofItems(EPBlocks.BASIC_MACHINE_FRAME_ITEM),
                'S', Ingredient.ofItems(ModBlocks.STOVE.get())
        ), new String[] {
                "ISI",
                "RBR",
                "ISI"
        }, new ItemStack(EPFDBlocks.ELECTRIC_STOVE_ITEM), CraftingRecipeCategory.MISC);

        addShapedCraftingRecipe(output, conditionsFromItem(EPBlocks.HARDENED_MACHINE_FRAME_ITEM), Map.of(
                'S', Ingredient.fromTag(CommonItemTags.INGOTS_STEEL),
                'B', Ingredient.ofItems(EPItems.BASIC_CIRCUIT),
                'H', Ingredient.ofItems(EPBlocks.HARDENED_MACHINE_FRAME_ITEM),
                'E', Ingredient.ofItems(EPFDBlocks.ELECTRIC_STOVE_ITEM)
        ), new String[] {
                "SES",
                "BHB",
                "SES"
        }, new ItemStack(EPFDBlocks.INDUCTION_STOVE_ITEM), CraftingRecipeCategory.MISC);
    }

    private void buildSawmillRecipes(RecipeExporter output) {
        addSawmillRecipe(output, Ingredient.ofItems(ModItems.CUTTING_BOARD.get()), new ItemStack(Items.OAK_PLANKS, 4),
                2, "oak_planks", "cutting_board");

        addSawmillRecipe(output, Ingredient.fromTag(ModTags.WOODEN_CABINETS), new ItemStack(Items.STICK, 10),
                3, "sticks", "cabinets");
    }

    private void buildPlantGrowthChamberRecipes(RecipeExporter output) {
        addPlantGrowthChamberRecipe(output, Ingredient.ofItems(ModItems.TOMATO_SEEDS.get()), new OutputItemStackWithPercentages[] {
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

        addPlantGrowthChamberRecipe(output, Ingredient.ofItems(ModItems.ONION.get()), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(ModItems.ONION.get()), new double[] {
                        1., .75, .25, .25
                })
        }, 16000, "onions", "onion");

        addPlantGrowthChamberRecipe(output, Ingredient.ofItems(ModItems.CABBAGE_SEEDS.get()), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(ModItems.CABBAGE_SEEDS.get()), new double[] {
                        1., .33, .33
                }),
                new OutputItemStackWithPercentages(new ItemStack(ModItems.CABBAGE.get()), new double[] {
                        1., .75, .25, .25
                })
        }, 16000, "cabbage", "cabbage_seeds");
    }
    private static void addShapedCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, "");
    }
    private static void addShapedCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category,
                                                String group) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, group, "");
    }
    private static void addShapedCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category,
                                                String group, String recipeIdSuffix) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, group, recipeIdSuffix, "");
    }
    private static void addShapedCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category,
                                                String group, String recipeIdSuffix, String recipeIdPrefix) {
        Identifier recipeId = Identifier.of(EnergizedPowerFDMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemPath(result.getItem()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = output.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .criterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        ShapedRecipe recipe = new ShapedRecipe(Objects.requireNonNullElse(group, ""),
                category, RawShapedRecipe.create(key, pattern), result);
        output.accept(recipeId, recipe, advancementBuilder.build(recipeId.withPrefixedPath("recipes/")));
    }
    private static void addShapelessCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingRecipeCategory category) {
        addShapelessCraftingRecipe(output, hasIngredientTrigger, inputs, result, category, "");
    }
    private static void addShapelessCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingRecipeCategory category,
                                                   String group) {
        addShapelessCraftingRecipe(output, hasIngredientTrigger, inputs, result, category, group, "");
    }
    private static void addShapelessCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingRecipeCategory category,
                                                   String group, String recipeIdSuffix) {
        addShapelessCraftingRecipe(output, hasIngredientTrigger, inputs, result, category, group, recipeIdSuffix, "");
    }
    private static void addShapelessCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingRecipeCategory category,
                                                   String group, String recipeIdSuffix, String recipeIdPrefix) {
        Identifier recipeId = Identifier.of(EnergizedPowerFDMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemPath(result.getItem()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = output.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .criterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        ShapelessRecipe recipe = new ShapelessRecipe(Objects.requireNonNullElse(group, ""), category, result,
                DefaultedList.copyOf(Ingredient.EMPTY, inputs.toArray(Ingredient[]::new)));
        output.accept(recipeId, recipe, advancementBuilder.build(recipeId.withPrefixedPath("recipes/")));
    }
    private static void addSawmillRecipe(RecipeExporter RecipeExporter, Ingredient input, ItemStack output,
                                         int sawdustAmount, String outputName, String recipeIngredientName) {
        Identifier recipeId = Identifier.of(EnergizedPowerFDMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillRecipe recipe = new SawmillRecipe(output, input, sawdustAmount);
        RecipeExporter.accept(recipeId, recipe, null);
    }

    private static void addPlantGrowthChamberRecipe(RecipeExporter RecipeExporter, Ingredient input,
                                                    OutputItemStackWithPercentages[] outputs, int ticks,
                                                    String outputName, String recipeIngredientName) {
        Identifier recipeId = Identifier.of(EnergizedPowerFDMod.MODID, PATH_PREFIX + "growing/" +
                outputName + "_from_growing_" + recipeIngredientName);

        PlantGrowthChamberRecipe recipe = new PlantGrowthChamberRecipe(outputs, input, ticks);
        RecipeExporter.accept(recipeId, recipe, null);
    }
}
