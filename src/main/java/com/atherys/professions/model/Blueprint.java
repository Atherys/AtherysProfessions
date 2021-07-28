package com.atherys.professions.model;

import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.util.List;

public class Blueprint {

    private String id;

    private List<ItemStackSnapshot> ingredients;

    private ItemStackSnapshot result;

    public Blueprint() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ItemStackSnapshot> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<ItemStackSnapshot> ingredients) {
        this.ingredients = ingredients;
    }

    public ItemStackSnapshot getResult() {
        return result;
    }

    public void setResult(ItemStackSnapshot result) {
        this.result = result;
    }
}
