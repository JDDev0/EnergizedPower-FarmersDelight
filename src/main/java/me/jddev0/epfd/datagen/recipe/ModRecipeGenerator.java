package me.jddev0.epfd.datagen.recipe;

import me.jddev0.ep.block.EPBlocks;
import me.jddev0.ep.item.EPItems;
import me.jddev0.ep.recipe.OutputItemStackTemplateWithPercentages;
import me.jddev0.ep.recipe.PlantGrowthChamberRecipe;
import me.jddev0.ep.recipe.SawmillRecipe;
import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.epfd.block.EPFDBlocks;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ModRecipeGenerator extends RecipeProvider {
    private final String FARMERS_DELIGHT_MOD_ID = FarmersDelight.MODID;
    private final String PATH_PREFIX = "compat/" + FARMERS_DELIGHT_MOD_ID + "/";

    public ModRecipeGenerator(HolderLookup.Provider registries, RecipeOutput exporter) {
        super(registries, exporter);
    }

    @Override
    public void buildRecipes() {
        buildCraftingRecipes();
        buildSawmillRecipes();
        buildPlantGrowthChamberRecipes();
    }

    private void buildCraftingRecipes() {
        buildMachineCraftingRecipes();
    }
    private void buildMachineCraftingRecipes() {
        addShapedCraftingRecipe(has(EPBlocks.BASIC_MACHINE_FRAME_ITEM), Map.of(
                'I', tag(CommonItemTags.PLATES_IRON),
                'R', tag(ConventionalItemTags.REDSTONE_DUSTS),
                'B', Ingredient.of(EPBlocks.BASIC_MACHINE_FRAME_ITEM),
                'S', Ingredient.of(ModBlocks.STOVE.get())
        ), new String[] {
                "ISI",
                "RBR",
                "ISI"
        }, new ItemStackTemplate(EPFDBlocks.ELECTRIC_STOVE_ITEM), CraftingBookCategory.MISC);

        addShapedCraftingRecipe(has(EPBlocks.HARDENED_MACHINE_FRAME_ITEM), Map.of(
                'S', tag(CommonItemTags.INGOTS_STEEL),
                'B', Ingredient.of(EPItems.BASIC_CIRCUIT),
                'H', Ingredient.of(EPBlocks.HARDENED_MACHINE_FRAME_ITEM),
                'E', Ingredient.of(EPFDBlocks.ELECTRIC_STOVE_ITEM)
        ), new String[] {
                "SES",
                "BHB",
                "SES"
        }, new ItemStackTemplate(EPFDBlocks.INDUCTION_STOVE_ITEM), CraftingBookCategory.MISC);
    }

    private void buildSawmillRecipes() {
        addSawmillRecipe(Ingredient.of(ModItems.CUTTING_BOARD.get()), new ItemStackTemplate(Items.OAK_PLANKS, 4),
                2, "oak_planks", "cutting_board");

        addSawmillRecipe(tag(ModTags.WOODEN_CABINETS), new ItemStackTemplate(Items.STICK, 10),
                3, "sticks", "cabinets");
    }

    private void buildPlantGrowthChamberRecipes() {
        addPlantGrowthChamberRecipe(Ingredient.of(ModItems.TOMATO_SEEDS.get()), new OutputItemStackTemplateWithPercentages[] {
                new OutputItemStackTemplateWithPercentages(new ItemStackTemplate(ModItems.TOMATO_SEEDS.get()), new double[] {
                        1.
                }),
                new OutputItemStackTemplateWithPercentages(new ItemStackTemplate(ModItems.TOMATO.get()), new double[] {
                        1., .75, .5, .25
                }),
                new OutputItemStackTemplateWithPercentages(new ItemStackTemplate(ModItems.ROTTEN_TOMATO.get()), new double[] {
                        .05
                })
        }, 16000, "tomatoes", "tomato_seeds");

        addPlantGrowthChamberRecipe(Ingredient.of(ModItems.ONION.get()), new OutputItemStackTemplateWithPercentages[] {
                new OutputItemStackTemplateWithPercentages(new ItemStackTemplate(ModItems.ONION.get()), new double[] {
                        1., .75, .25, .25
                })
        }, 16000, "onions", "onion");

        addPlantGrowthChamberRecipe(Ingredient.of(ModItems.CABBAGE_SEEDS.get()), new OutputItemStackTemplateWithPercentages[] {
                new OutputItemStackTemplateWithPercentages(new ItemStackTemplate(ModItems.CABBAGE_SEEDS.get()), new double[] {
                        1., .33, .33
                }),
                new OutputItemStackTemplateWithPercentages(new ItemStackTemplate(ModItems.CABBAGE.get()), new double[] {
                        1., .75, .25, .25
                })
        }, 16000, "cabbage", "cabbage_seeds");
    }
    private void addShapedCraftingRecipe(Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                         Map<Character, Ingredient> key, String[] pattern,
                                         ItemStackTemplate result, CraftingBookCategory category) {
        addShapedCraftingRecipe(hasIngredientTrigger, key, pattern, result, category, "");
    }
    private void addShapedCraftingRecipe(Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                         Map<Character, Ingredient> key, String[] pattern,
                                         ItemStackTemplate result, CraftingBookCategory category,
                                         String group) {
        addShapedCraftingRecipe(hasIngredientTrigger, key, pattern, result, category, group, "");
    }
    private void addShapedCraftingRecipe(Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                         Map<Character, Ingredient> key, String[] pattern,
                                         ItemStackTemplate result, CraftingBookCategory category,
                                         String group, String recipeIdSuffix) {
        addShapedCraftingRecipe(hasIngredientTrigger, key, pattern, result, category, group, recipeIdSuffix, "");
    }
    private void addShapedCraftingRecipe(Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                         Map<Character, Ingredient> key, String[] pattern,
                                         ItemStackTemplate result, CraftingBookCategory category,
                                         String group, String recipeIdSuffix, String recipeIdPrefix) {
        Identifier recipeId = Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemName(result.item().value()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(getKey(recipeId)))
                .addCriterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(getKey(recipeId)))
                .requirements(AdvancementRequirements.Strategy.OR);
        ShapedRecipe recipe = new ShapedRecipe(new Recipe.CommonInfo(true),
                new CraftingRecipe.CraftingBookInfo(category, Objects.requireNonNullElse(group, "")),
                ShapedRecipePattern.of(key, pattern), result);
        this.output.accept(getKey(recipeId), recipe, advancementBuilder.build(recipeId.withPrefix("recipes/")));
    }
    private void addShapelessCraftingRecipe(Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                            List<Ingredient> inputs, ItemStackTemplate result, CraftingBookCategory category) {
        addShapelessCraftingRecipe(hasIngredientTrigger, inputs, result, category, "");
    }
    private void addShapelessCraftingRecipe(Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                            List<Ingredient> inputs, ItemStackTemplate result, CraftingBookCategory category,
                                            String group) {
        addShapelessCraftingRecipe(hasIngredientTrigger, inputs, result, category, group, "");
    }
    private void addShapelessCraftingRecipe(Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                            List<Ingredient> inputs, ItemStackTemplate result, CraftingBookCategory category,
                                            String group, String recipeIdSuffix) {
        addShapelessCraftingRecipe(hasIngredientTrigger, inputs, result, category, group, recipeIdSuffix, "");
    }
    private void addShapelessCraftingRecipe(Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                            List<Ingredient> inputs, ItemStackTemplate result, CraftingBookCategory category,
                                            String group, String recipeIdSuffix, String recipeIdPrefix) {
        Identifier recipeId = Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemName(result.item().value()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(getKey(recipeId)))
                .addCriterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(getKey(recipeId)))
                .requirements(AdvancementRequirements.Strategy.OR);
        ShapelessRecipe recipe = new ShapelessRecipe(new Recipe.CommonInfo(true),
                new CraftingRecipe.CraftingBookInfo(category, Objects.requireNonNullElse(group, "")), result,
                NonNullList.of(null, inputs.toArray(Ingredient[]::new)));
        this.output.accept(getKey(recipeId), recipe, advancementBuilder.build(recipeId.withPrefix("recipes/")));
    }
    private void addSawmillRecipe(Ingredient input, ItemStackTemplate output,
                                  int sawdustAmount, String outputName, String recipeIngredientName) {
        Identifier recipeId = Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillRecipe recipe = new SawmillRecipe(output, input, sawdustAmount);
        this.output.accept(getKey(recipeId), recipe, null);
    }

    private void addPlantGrowthChamberRecipe(Ingredient input, OutputItemStackTemplateWithPercentages[] outputs, int ticks,
                                             String outputName, String recipeIngredientName) {
        Identifier recipeId = Identifier.fromNamespaceAndPath(EnergizedPowerFDMod.MODID, PATH_PREFIX + "growing/" +
                outputName + "_from_growing_" + recipeIngredientName);

        PlantGrowthChamberRecipe recipe = new PlantGrowthChamberRecipe(outputs, input, ticks);
        this.output.accept(getKey(recipeId), recipe, null);
    }

    private ResourceKey<Recipe<?>> getKey(Identifier recipeId) {
        return ResourceKey.create(Registries.RECIPE, recipeId);
    }
}
