package com.atherys.professions.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.text.Text;

@ConfigSerializable
public class ProfessionConfig {

    @Setting("id")
    private String id;

    @Setting("name")
    private Text name;
    

}
