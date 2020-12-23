package com.atherys.professions.api;

import org.spongepowered.api.item.inventory.InventoryArchetypes;

public final class CraftingTypes {

    public static final CraftingType CRAFTING_TABLE = new CraftingType("atherysprofessions:crafting_table", "Crafting Table", InventoryArchetypes.WORKBENCH);

    public static final CraftingType FURNACE = new CraftingType("atherysprofessions:furnace", "Furnace", InventoryArchetypes.FURNACE);

    public static final CraftingType ANVIL = new CraftingType("atherysprofessions:anvil", "Anvil", InventoryArchetypes.ANVIL);

    public static final CraftingType BREWING_STAND = new CraftingType("atherysprofessions:brewing_stand", "Brewing Stand", InventoryArchetypes.BREWING_STAND);

}
