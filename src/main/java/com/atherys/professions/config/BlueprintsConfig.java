package com.atherys.professions.config;

import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigSerializable
public class BlueprintsConfig extends PluginConfig {

    @Setting("blueprints")
    public List<RecipeConfig> BLUEPRINTS = new ArrayList<>();
    {
        BLUEPRINTS.add(new RecipeConfig());
    }

    protected BlueprintsConfig() throws IOException {
        super("./config/atherysprofessions", "blueprints.conf");
        init();
    }
}
