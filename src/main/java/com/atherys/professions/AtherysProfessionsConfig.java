package com.atherys.professions;

import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.io.IOException;

@ConfigSerializable
public class AtherysProfessionsConfig extends PluginConfig {
    protected AtherysProfessionsConfig() throws IOException {
        super("./config/atherysprofessions", "config.conf");
        init();
    }
}
