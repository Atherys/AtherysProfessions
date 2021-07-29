package com.atherys.professions.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class BlueprintConfig {
    @Setting("recipe")
    public RecipeConfig RECIPE = new RecipeConfig();

    @Setting("rpg-item")
    public String RPG_ITEM;
}
