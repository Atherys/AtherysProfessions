package com.atherys.professions.service;

import com.atherys.professions.config.RecipeConfig;
import com.atherys.professions.config.RecipesConfig;
import com.atherys.rpg.AtherysRPG;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.game.GameRegistryEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe;
import org.spongepowered.api.item.recipe.crafting.Ingredient;
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe;
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class RecipeService {
    @Inject
    private RecipesConfig recipesConfig;

    public void loadRecipes(GameRegistryEvent.Register<CraftingRecipe> event) {
        for (RecipeConfig recipeConfig : recipesConfig.RECIPES) {
            CraftingRecipe recipe;
            if (recipeConfig.IS_SHAPELESS) {
                recipe = loadShapelessRecipe(recipeConfig);
            } else {
                recipe = loadShapedRecipe(recipeConfig);
            }

            event.register(recipe);
        }
    }

    private CraftingRecipe loadShapedRecipe(RecipeConfig recipeConfig) {
        List<Ingredient> ingredients = toIngredients(recipeConfig.INGREDIENTS);
        ShapedCraftingRecipe.Builder.ResultStep builder;

        if (ingredients.size() == 4) {
            builder = ShapedCraftingRecipe.builder().rows()
                    .row(ingredients.get(0), ingredients.get(1))
                    .row(ingredients.get(2), ingredients.get(3));
        } else {
            builder = ShapedCraftingRecipe.builder().rows()
                    .row(ingredients.get(0), ingredients.get(1), ingredients.get(2))
                    .row(ingredients.get(3), ingredients.get(4), ingredients.get(5))
                    .row(ingredients.get(6), ingredients.get(7), ingredients.get(8));
        }

        ItemStackSnapshot result = AtherysRPG.getInstance().getItemFacade().getCachedItems().getOrDefault(
                recipeConfig.RESULT.ITEM,
                ItemStack.of(getItemType(recipeConfig.RESULT.ITEM), recipeConfig.RESULT.QUANTITY).createSnapshot()
        );

        return builder.result(result).id(recipeConfig.ID).build();
    }

    private CraftingRecipe loadShapelessRecipe(RecipeConfig recipeConfig) {
        ShapelessCraftingRecipe.Builder builder = CraftingRecipe.shapelessBuilder();

        toIngredients(recipeConfig.INGREDIENTS)
                .forEach(builder::addIngredient);

        return builder.addIngredient(Ingredient.NONE)
                .result(getStackForItemConfig(recipeConfig.RESULT))
                .id(recipeConfig.ID)
                .build();
    }

    private List<Ingredient> toIngredients(List<RecipeConfig.ItemConfig> itemConfigs) {
        return itemConfigs.stream()
                .map(this::getStackForItemConfig)
                .map(Ingredient::of)
                .collect(Collectors.toList());
    }

    public ItemStackSnapshot getStackForItemConfig(RecipeConfig.ItemConfig result) {
        if (result.ITEM.isEmpty()) {
            return ItemStackSnapshot.NONE;
        }
        ItemStackSnapshot snapshot = AtherysRPG.getInstance().getItemFacade().getCachedItems().get(result.ITEM);

        if (snapshot == null) {
            snapshot = ItemStack.of(getItemType(result.ITEM), result.QUANTITY).createSnapshot();
        } else {
            snapshot = ItemStack.builder()
                    .fromSnapshot(snapshot)
                    .quantity(result.QUANTITY)
                    .build()
                    .createSnapshot();
        }

        return snapshot;
    }

    private ItemType getItemType(String id) {
        return Sponge.getRegistry().getType(ItemType.class, id).get();
    }
}
