package com.atherys.professions.api;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(CraftingTypes.class)
public class CraftingType implements CatalogType {

    private String id;

    private String name;

    private InventoryArchetype inventoryArchetype;

    protected CraftingType(String id, String name, InventoryArchetype inventoryArchetype) {
        this.id = id;
        this.name = name;
        this.inventoryArchetype = inventoryArchetype;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public InventoryArchetype getInventoryArchetype() {
        return inventoryArchetype;
    }
}
