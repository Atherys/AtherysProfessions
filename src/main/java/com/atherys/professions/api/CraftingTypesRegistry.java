package com.atherys.professions.api;

import org.spongepowered.api.registry.CatalogRegistryModule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CraftingTypesRegistry implements CatalogRegistryModule<CraftingType> {
    private Map<String, CraftingType> craftingTypes = new HashMap<>();

    public CraftingTypesRegistry() {
        craftingTypes.put(CraftingTypes.ANVIL.getId(), CraftingTypes.ANVIL);
        craftingTypes.put(CraftingTypes.CRAFTING_TABLE.getId(), CraftingTypes.CRAFTING_TABLE);
        craftingTypes.put(CraftingTypes.BREWING_STAND.getId(), CraftingTypes.BREWING_STAND);
        craftingTypes.put(CraftingTypes.FURNACE.getId(), CraftingTypes.FURNACE);
    }

    @Override
    public Optional<CraftingType> getById(String id) {
        return Optional.ofNullable(craftingTypes.get(id));
    }

    @Override
    public Collection<CraftingType> getAll() {
        return craftingTypes.values();
    }
}
