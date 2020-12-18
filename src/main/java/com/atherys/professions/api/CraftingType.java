package com.atherys.professions.api;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(CraftingTypes.class)
public class CraftingType implements CatalogType {

    private String id;

    private String name;

    protected CraftingType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
