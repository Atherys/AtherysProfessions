package com.atherys.professions.config;

import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ConfigSerializable
public class RepairsConfig extends PluginConfig {
    public RepairsConfig() throws IOException {
        super("./config/atherysprofessions/", "repairs.conf");
    }

    public Map<ItemType, Double> REPAIR_PERCENTS = new HashMap<>();

    {
        REPAIR_PERCENTS.put(ItemTypes.DIAMOND_AXE, 0.25);
    }
}
