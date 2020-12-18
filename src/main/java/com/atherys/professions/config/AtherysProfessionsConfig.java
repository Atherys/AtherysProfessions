package com.atherys.professions.config;

import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class AtherysProfessionsConfig extends PluginConfig {

    @Setting("professions")
    List<ProfessionConfig> PROFESSIONS = new ArrayList<>();

    protected AtherysProfessionsConfig() throws IOException {
        super("./config/atherysprofessions", "config.conf");
    }
}
