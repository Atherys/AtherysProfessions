package com.atherys.professions.config;

import com.atherys.core.utils.PluginConfig;
import com.google.inject.Singleton;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Singleton
@ConfigSerializable
public class RecipesConfig extends PluginConfig {
    @Setting("recipes")
    public List<RecipeConfig> RECIPES = Arrays.asList(new RecipeConfig());

    public RecipesConfig() throws IOException {
        super("config/atherysprofessions/", "recipes.conf");
        init();
    }
}
