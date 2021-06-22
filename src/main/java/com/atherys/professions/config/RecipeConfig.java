package com.atherys.professions.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.Collections;
import java.util.List;

@ConfigSerializable
public class RecipeConfig {

    @Setting("result")
    public ItemConfig RESULT = new ItemConfig();

    @Setting("ingredients")
    public List<ItemConfig> INGREDIENTS = Collections.singletonList(new ItemConfig());

    @Setting("id")
    public String ID = "custom_apple";

    @Setting("is-shapeless")
    public boolean IS_SHAPELESS = true;

    @ConfigSerializable
    public static class ItemConfig {
        @Setting("item")
        public String ITEM = "minecraft:apple";

        @Setting("quantity")
        public int QUANTITY = 2;
    }
}
