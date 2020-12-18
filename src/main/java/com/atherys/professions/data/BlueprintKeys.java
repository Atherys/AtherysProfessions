package com.atherys.professions.data;

import com.google.common.reflect.TypeToken;
import javax.annotation.Generated;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.key.KeyFactory;
import org.spongepowered.api.data.value.mutable.Value;

@Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2020-12-18T19:55:18.030Z")
public class BlueprintKeys {

    private BlueprintKeys() {}

    public final static Key<Value<String>> BLUEPRINT_ID = KeyFactory.makeSingleKey(TypeToken.of(String.class), new TypeToken<Value<String>>(){}, DataQuery.of("BlueprintId"), "atherysprofessions:blueprintid", "Blueprint Id");

    public final static Key<Value<Boolean>> IS_ORIGINAL = KeyFactory.makeSingleKey(TypeToken.of(Boolean.class), new TypeToken<Value<Boolean>>(){}, DataQuery.of("IsOriginal"), "atherysprofessions:isoriginal", "Is Original");

    public final static Key<Value<Double>> EFFICIENCY = KeyFactory.makeSingleKey(TypeToken.of(Double.class), new TypeToken<Value<Double>>(){}, DataQuery.of("Efficiency"), "atherysprofessions:efficiency", "Efficiency");

}
