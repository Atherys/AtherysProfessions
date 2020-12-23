package com.atherys.professions.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class BlueprintIngredientConfig {

    @Setting("acceptable-items")
    private List<ItemStackSnapshot> acceptableItems = new ArrayList<>();

    public BlueprintIngredientConfig() {
    }

    public List<ItemStackSnapshot> getAcceptableItems() {
        return acceptableItems;
    }

    public void setAcceptableItems(List<ItemStackSnapshot> acceptableItems) {
        this.acceptableItems = acceptableItems;
    }
}
