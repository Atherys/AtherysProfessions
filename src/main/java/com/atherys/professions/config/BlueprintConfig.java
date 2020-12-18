package com.atherys.professions.config;

import com.atherys.professions.api.CraftingType;
import com.atherys.professions.api.CraftingTypes;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class BlueprintConfig {

    @Setting("id")
    String id;

    @Setting("permission")
    String permission;

    @Setting("crafting-type")
    CraftingType craftingType = CraftingTypes.CRAFTING_TABLE;

    @Setting("materials")
    List<ItemStackSnapshot> materials = new ArrayList<>();

    @Setting("result")
    ItemStackSnapshot result;

    public BlueprintConfig() {
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

    public List<ItemStackSnapshot> getMaterials() {
        return materials;
    }

    public void setMaterials(List<ItemStackSnapshot> materials) {
        this.materials = materials;
    }

    public ItemStackSnapshot getResult() {
        return result;
    }

    public void setResult(ItemStackSnapshot result) {
        this.result = result;
    }
}
