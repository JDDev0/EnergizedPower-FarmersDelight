package me.jddev0.epfd.datagen;

import me.jddev0.ep.block.EPBlocks;
import me.jddev0.ep.datagen.recipe.PlantGrowthChamberFinishedRecipe;
import me.jddev0.ep.datagen.recipe.SawmillFinishedRecipe;
import me.jddev0.ep.datagen.recipe.ShapedFinishedRecipe;
import me.jddev0.ep.datagen.recipe.ShapelessFinishedRecipe;
import me.jddev0.ep.item.EPItems;
import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.epfd.EnergizedPowerFDMod;
import me.jddev0.ep.recipe.*;
import me.jddev0.epfd.block.EPFDBlocks;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final String FARMERS_DELIGHT_MOD_ID = FarmersDelight.MODID;
    private static final String PATH_PREFIX = "compat/" + FARMERS_DELIGHT_MOD_ID + "/";

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> output) {
        buildCraftingRecipes(output);
        buildSawmillRecipes(output);
        buildPlantGrowthChamberRecipes(output);
    }

    private void buildCraftingRecipes(Consumer<FinishedRecipe> output) {
        buildMachineCraftingRecipes(output);
    }
    private void buildMachineCraftingRecipes(Consumer<FinishedRecipe> output) {
        addShapedCraftingRecipe(output, has(EPBlocks.BASIC_MACHINE_FRAME_ITEM), Map.of(
                'I', Ingredient.of(CommonItemTags.PLATES_IRON),
                'R', Ingredient.of(Tags.Items.DUSTS_REDSTONE),
                'B', Ingredient.of(EPBlocks.BASIC_MACHINE_FRAME_ITEM.get()),
                'S', Ingredient.of(ModBlocks.STOVE.get())
        ), new String[] {
                "ISI",
                "RBR",
                "ISI"
        }, new ItemStack(EPFDBlocks.ELECTRIC_STOVE_ITEM.get()), CraftingBookCategory.MISC);

        addShapedCraftingRecipe(output, has(EPBlocks.HARDENED_MACHINE_FRAME_ITEM), Map.of(
                'S', Ingredient.of(CommonItemTags.INGOTS_STEEL),
                'B', Ingredient.of(EPItems.BASIC_CIRCUIT.get()),
                'H', Ingredient.of(EPBlocks.HARDENED_MACHINE_FRAME_ITEM.get()),
                'E', Ingredient.of(EPFDBlocks.ELECTRIC_STOVE_ITEM.get())
        ), new String[] {
                "SES",
                "BHB",
                "SES"
        }, new ItemStack(EPFDBlocks.INDUCTION_STOVE_ITEM.get()), CraftingBookCategory.MISC);
    }

    private void buildSawmillRecipes(Consumer<FinishedRecipe> output) {
        addSawmillRecipe(output, Ingredient.of(ModItems.CUTTING_BOARD.get()), new ItemStack(Items.OAK_PLANKS, 4),
                2, "oak_planks", "cutting_board");

        addSawmillRecipe(output, Ingredient.of(ModTags.WOODEN_CABINETS), new ItemStack(Items.STICK, 10),
                3, "sticks", "cabinets");
    }

    private void buildPlantGrowthChamberRecipes(Consumer<FinishedRecipe> output) {
        addPlantGrowthChamberRecipe(output, Ingredient.of(ModItems.TOMATO_SEEDS.get()), new OutputItemStackWithPercentages[] {
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

        addPlantGrowthChamberRecipe(output, Ingredient.of(ModItems.ONION.get()), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(ModItems.ONION.get()), new double[] {
                        1., .75, .25, .25
                })
        }, 16000, "onions", "onion");

        addPlantGrowthChamberRecipe(output, Ingredient.of(ModItems.CABBAGE_SEEDS.get()), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(ModItems.CABBAGE_SEEDS.get()), new double[] {
                        1., .33, .33
                }),
                new OutputItemStackWithPercentages(new ItemStack(ModItems.CABBAGE.get()), new double[] {
                        1., .75, .25, .25
                })
        }, 16000, "cabbage", "cabbage_seeds");
    }
    private static void addShapedCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance  hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingBookCategory category) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, "");
    }
    private static void addShapedCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance  hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingBookCategory category,
                                                String group) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, group, "");
    }
    private static void addShapedCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance  hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingBookCategory category,
                                                String group, String recipeIdSuffix) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, group, recipeIdSuffix, "");
    }
    private static void addShapedCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance  hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingBookCategory category,
                                                String group, String recipeIdSuffix, String recipeIdPrefix) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerFDMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemName(result.getItem()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .addCriterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);
        ShapedFinishedRecipe recipe = new ShapedFinishedRecipe(
                recipeId,
                Objects.requireNonNullElse(group, ""),
                category, key, pattern, result,
                advancementBuilder,
                recipeId.withPrefix("recipes/")
        );
        output.accept(recipe);
    }
    private static void addShapelessCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance  hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingBookCategory category) {
        addShapelessCraftingRecipe(output, hasIngredientTrigger, inputs, result, category, "");
    }
    private static void addShapelessCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance  hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingBookCategory category,
                                                   String group) {
        addShapelessCraftingRecipe(output, hasIngredientTrigger, inputs, result, category, group, "");
    }
    private static void addShapelessCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance  hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingBookCategory category,
                                                   String group, String recipeIdSuffix) {
        addShapelessCraftingRecipe(output, hasIngredientTrigger, inputs, result, category, group, recipeIdSuffix, "");
    }
    private static void addShapelessCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance  hasIngredientTrigger,
                                                   List<Ingredient> inputs, ItemStack result, CraftingBookCategory category,
                                                   String group, String recipeIdSuffix, String recipeIdPrefix) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerFDMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemName(result.getItem()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .addCriterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);
        ShapelessFinishedRecipe recipe = new ShapelessFinishedRecipe(
                recipeId,
                Objects.requireNonNullElse(group, ""), category, result,
                NonNullList.of(Ingredient.EMPTY, inputs.toArray(Ingredient[]::new)),
                advancementBuilder,
                recipeId.withPrefix("recipes/")
        );
        output.accept(recipe);
    }
    private void addSawmillRecipe(Consumer<FinishedRecipe> recipeOutput, Ingredient input, ItemStack output,
                                         int sawdustAmount, String outputName, String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerFDMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillFinishedRecipe recipe = new SawmillFinishedRecipe(
                recipeId,
                output, input, sawdustAmount
        );
        recipeOutput.accept(recipe);
    }

    private void addPlantGrowthChamberRecipe(Consumer<FinishedRecipe> recipeOutput, Ingredient input,
                                             OutputItemStackWithPercentages[] outputs, int ticks,
                                             String outputName, String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerFDMod.MODID, PATH_PREFIX + "growing/" +
                outputName + "_from_growing_" + recipeIngredientName);

        PlantGrowthChamberFinishedRecipe recipe = new PlantGrowthChamberFinishedRecipe(
                recipeId,
                outputs, input, ticks
        );
        recipeOutput.accept(recipe);
    }

    private static InventoryChangeTrigger.TriggerInstance has(RegistryObject<Item> item) {
        return has(item.get());
    }
}
