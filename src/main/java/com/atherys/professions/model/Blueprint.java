package com.atherys.professions.model;

import com.atherys.professions.api.CraftingType;
import com.atherys.professions.api.CraftingTypes;
import ninja.leaping.configurate.objectmapping.Setting;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.recipe.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Blueprint {

    private String id;

    private String permission;

    private CraftingType craftingType;

    private List<BlueprintIngredient> ingredients;

    private ItemStackSnapshot result;

    public Blueprint() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public CraftingType getCraftingType() {
        return craftingType;
    }

    public void setCraftingType(CraftingType craftingType) {
        this.craftingType = craftingType;
    }

    public List<BlueprintIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<BlueprintIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ItemStackSnapshot getResult() {
        return result;
    }

    public void setResult(ItemStackSnapshot result) {
        this.result = result;
    }
}
