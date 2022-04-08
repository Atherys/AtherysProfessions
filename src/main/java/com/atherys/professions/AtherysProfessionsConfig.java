package com.atherys.professions;

import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.block.BlockType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ConfigSerializable
public class AtherysProfessionsConfig extends PluginConfig {
    @Setting("growth-timers")
    public Map<BlockType, Double> CROP_CHANCES = new HashMap<>();

    protected AtherysProfessionsConfig() throws IOException {
        super("./config/atherysprofessions", "config.conf");
        init();
    }
}
