package com.atherys.professions.data;

import java.util.Optional;
import javax.annotation.Generated;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;

@Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2020-12-18T20:31:47.616Z")
public class BlueprintData extends AbstractData<BlueprintData, BlueprintData.Immutable> {

    // The id of the blueprint. Used to lookup the blueprint in the config.
    private String blueprintId;

    // if the blueprint is an original.
    // If this is true, the item representing the blueprint will not be consumed when crafting.
    private Boolean isOriginal;

    // Efficiency of the blueprint
    // This can be either positive, or negative.
    // A negative efficiency represents an additional quantity of materials that are required ( rounded up )
    // A positive efficiency represents a lesser quantity of materials that are consumed ( rounded down )
    // +10% ( +0.10 ) with a blueprint which requires 10 wood planks = 9 wood planks
    // -10% ( -0.10 ) with a blueprint which requires 10 wood planks = 11 wood planks
    // Efficiency can be improved maybe?
    private Double efficiency;

    {
        registerGettersAndSetters();
    }

    BlueprintData() {
        blueprintId = "";
    }

    BlueprintData(Boolean isOriginal, String blueprintId, Double efficiency) {
        this.isOriginal = isOriginal;
        this.blueprintId = blueprintId;
        this.efficiency = efficiency;
    }

    @Override
    protected void registerGettersAndSetters() {
        registerFieldGetter(BlueprintKeys.IS_ORIGINAL, this::getIsOriginal);
        registerFieldSetter(BlueprintKeys.IS_ORIGINAL, this::setIsOriginal);
        registerKeyValue(BlueprintKeys.IS_ORIGINAL, this::isOriginal);
        registerFieldGetter(BlueprintKeys.BLUEPRINT_ID, this::getBlueprintId);
        registerFieldSetter(BlueprintKeys.BLUEPRINT_ID, this::setBlueprintId);
        registerKeyValue(BlueprintKeys.BLUEPRINT_ID, this::blueprintId);
        registerFieldGetter(BlueprintKeys.EFFICIENCY, this::getEfficiency);
        registerFieldSetter(BlueprintKeys.EFFICIENCY, this::setEfficiency);
        registerKeyValue(BlueprintKeys.EFFICIENCY, this::efficiency);
    }

    public Boolean getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Boolean isOriginal) {
        this.isOriginal = isOriginal;
    }

    public Value<Boolean> isOriginal() {
        return Sponge.getRegistry().getValueFactory().createValue(BlueprintKeys.IS_ORIGINAL, isOriginal);
    }

    public String getBlueprintId() {
        return blueprintId;
    }

    public void setBlueprintId(String blueprintId) {
        this.blueprintId = blueprintId;
    }

    public Value<String> blueprintId() {
        return Sponge.getRegistry().getValueFactory().createValue(BlueprintKeys.BLUEPRINT_ID, blueprintId);
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Value<Double> efficiency() {
        return Sponge.getRegistry().getValueFactory().createValue(BlueprintKeys.EFFICIENCY, efficiency);
    }

    @Override
    public Optional<BlueprintData> fill(DataHolder dataHolder, MergeFunction overlap) {
        dataHolder.get(BlueprintData.class).ifPresent(that -> {
                BlueprintData data = overlap.merge(this, that);
                this.isOriginal = data.isOriginal;
                this.blueprintId = data.blueprintId;
                this.efficiency = data.efficiency;
        });
        return Optional.of(this);
    }

    @Override
    public Optional<BlueprintData> from(DataContainer container) {
        return from((DataView) container);
    }

    public Optional<BlueprintData> from(DataView container) {
        container.getObject(BlueprintKeys.IS_ORIGINAL.getQuery(), Boolean.class).ifPresent(v -> isOriginal = v);
        container.getObject(BlueprintKeys.BLUEPRINT_ID.getQuery(), String.class).ifPresent(v -> blueprintId = v);
        container.getObject(BlueprintKeys.EFFICIENCY.getQuery(), Double.class).ifPresent(v -> efficiency = v);
        return Optional.of(this);
    }

    @Override
    public BlueprintData copy() {
        return new BlueprintData(isOriginal, blueprintId, efficiency);
    }

    @Override
    public Immutable asImmutable() {
        return new Immutable(isOriginal, blueprintId, efficiency);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer()
                .set(BlueprintKeys.IS_ORIGINAL.getQuery(), isOriginal)
                .set(BlueprintKeys.BLUEPRINT_ID.getQuery(), blueprintId)
                .set(BlueprintKeys.EFFICIENCY.getQuery(), efficiency);
    }

    @Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2020-12-18T20:31:47.641Z")
    public static class Immutable extends AbstractImmutableData<Immutable, BlueprintData> {

        private Boolean isOriginal;
        private String blueprintId;
        private Double efficiency;
        {
            registerGetters();
        }

        Immutable() {
            blueprintId = "";
        }

        Immutable(Boolean isOriginal, String blueprintId, Double efficiency) {
            this.isOriginal = isOriginal;
            this.blueprintId = blueprintId;
            this.efficiency = efficiency;
        }

        @Override
        protected void registerGetters() {
            registerFieldGetter(BlueprintKeys.IS_ORIGINAL, this::getIsOriginal);
            registerKeyValue(BlueprintKeys.IS_ORIGINAL, this::isOriginal);
            registerFieldGetter(BlueprintKeys.BLUEPRINT_ID, this::getBlueprintId);
            registerKeyValue(BlueprintKeys.BLUEPRINT_ID, this::blueprintId);
            registerFieldGetter(BlueprintKeys.EFFICIENCY, this::getEfficiency);
            registerKeyValue(BlueprintKeys.EFFICIENCY, this::efficiency);
        }

        public Boolean getIsOriginal() {
            return isOriginal;
        }

        public ImmutableValue<Boolean> isOriginal() {
            return Sponge.getRegistry().getValueFactory().createValue(BlueprintKeys.IS_ORIGINAL, isOriginal).asImmutable();
        }

        public String getBlueprintId() {
            return blueprintId;
        }

        public ImmutableValue<String> blueprintId() {
            return Sponge.getRegistry().getValueFactory().createValue(BlueprintKeys.BLUEPRINT_ID, blueprintId).asImmutable();
        }

        public Double getEfficiency() {
            return efficiency;
        }

        public ImmutableValue<Double> efficiency() {
            return Sponge.getRegistry().getValueFactory().createValue(BlueprintKeys.EFFICIENCY, efficiency).asImmutable();
        }

        @Override
        public BlueprintData asMutable() {
            return new BlueprintData(isOriginal, blueprintId, efficiency);
        }

        @Override
        public int getContentVersion() {
            return 1;
        }

        @Override
        public DataContainer toContainer() {
            return super.toContainer()
                    .set(BlueprintKeys.IS_ORIGINAL.getQuery(), isOriginal)
                    .set(BlueprintKeys.BLUEPRINT_ID.getQuery(), blueprintId)
                    .set(BlueprintKeys.EFFICIENCY.getQuery(), efficiency);
        }

    }

    @Generated(value = "flavor.pie.generator.data.DataManipulatorGenerator", date = "2020-12-18T20:31:47.645Z")
    public static class Builder extends AbstractDataBuilder<BlueprintData> implements DataManipulatorBuilder<BlueprintData, Immutable> {

        protected Builder() {
            super(BlueprintData.class, 1);
        }

        @Override
        public BlueprintData create() {
            return new BlueprintData();
        }

        @Override
        public Optional<BlueprintData> createFrom(DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override
        protected Optional<BlueprintData> buildContent(DataView container) throws InvalidDataException {
            return create().from(container);
        }

    }
}
