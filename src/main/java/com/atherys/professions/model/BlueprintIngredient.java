package com.atherys.professions.model;

import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BlueprintIngredient {

    private List<ItemStackSnapshot> acceptableItems = new ArrayList<>();

    public BlueprintIngredient() {
    }

    public List<ItemStackSnapshot> getAcceptableItems() {
        return acceptableItems;
    }

    public void setAcceptableItems(List<ItemStackSnapshot> acceptableItems) {
        this.acceptableItems = acceptableItems;
    }
}
