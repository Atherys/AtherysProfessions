package com.atherys.professions.config;

import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class BlueprintsConfig extends PluginConfig {

    @Setting("blueprints")
    public List<BlueprintConfig> BLUEPRINTS = new ArrayList<>();

    protected BlueprintsConfig() throws IOException {
        super("./config/atherysprofessions", "blueprints.conf");
    }
}
