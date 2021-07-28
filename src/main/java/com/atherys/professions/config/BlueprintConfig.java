package com.atherys.professions.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class BlueprintConfig {
    @Setting("id")
    public String ID;

    @Setting("ingredients")
    public List<RecipeConfig.ItemConfig> INGREDIENTS = new ArrayList<>();

    @Setting("result")
    public RecipeConfig.ItemConfig RESULT;
}
