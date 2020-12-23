package com.atherys.professions;

import com.atherys.core.AtherysCore;
import com.atherys.core.command.CommandService;
import com.atherys.core.event.AtherysHibernateInitializedEvent;
import com.atherys.core.facade.MessagingFacade;
import com.atherys.professions.api.CraftingType;
import com.atherys.professions.api.CraftingTypesRegistry;
import com.atherys.professions.command.MakeBlueprintCommand;
import com.atherys.professions.config.AtherysProfessionsConfig;
import com.atherys.professions.config.BlueprintsConfig;
import com.atherys.professions.data.BlueprintData;
import com.atherys.professions.data.BlueprintKeys;
import com.atherys.professions.facade.BlueprintFacade;
import com.atherys.professions.facade.ProfessionsMessagingFacade;
import com.atherys.professions.listener.CraftingListener;
import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.MapValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.GameRegistryEvent;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "atherysprofessions",
        name = "A'therys Professions",
        description = "A professions plugin for the A'therys Horizons server",
        version = "%PROJECT_VERSION%",
        dependencies = {
                @Dependency(id = "atheryscore")
        }
)
public class AtherysProfessions {

    private static AtherysProfessions instance;

    private static boolean init = false;

    @Inject
    Logger logger;

    @Inject
    Injector injector;

    private Components components;

    private Injector professionsInjector;

    public static AtherysProfessions getInstance() {
        return instance;
    }

    private void init() {
        instance = this;

        components = new Components();

        professionsInjector = injector.createChildInjector();
        professionsInjector.injectMembers(components);

        try {
            AtherysCore.getCommandService().register(new MakeBlueprintCommand(), this);
        } catch (CommandService.AnnotatedCommandException e) {
            e.printStackTrace();
        }

        Sponge.getEventManager().registerListeners(this, components.craftingListener);

        init = true;
    }

    private void start() {
        getBlueprintFacade().init();
    }

    private void stop() {

    }

    private void reload() {
        components.config.load();
        components.blueprintsConfig.load();

        getBlueprintFacade().init();
    }

    @Listener
    public void onInit(AtherysHibernateInitializedEvent event) {
        init();
    }

    @Listener(order = Order.LATE)
    public void onStart(GameStartedServerEvent event) {
        if (init) start();
    }

    @Listener
    public void onReload(GameReloadEvent event) {
        if (init) reload();
    }

    @Listener
    public void onStop(GameStoppingServerEvent event) {
        if (init) stop();
    }

    @Listener
    public void onKeyRegistration(GameRegistryEvent.Register<Key<?>> event) {
        BlueprintKeys.BLUEPRINT_ID = Key.builder()
                .id("blueprintid")
                .name("Blueprint Id")
                .query(DataQuery.of("Blueprint_Id"))
                .type(new TypeToken<Value<String>>() {})
                .build();

        BlueprintKeys.IS_ORIGINAL = Key.builder()
                .id("isoriginal")
                .name("Is Original?")
                .query(DataQuery.of("Is_Original"))
                .type(new TypeToken<Value<Boolean>>() {})
                .build();

        BlueprintKeys.EFFICIENCY = Key.builder()
                .id("efficiency")
                .name("Efficiency")
                .query(DataQuery.of("Efficiency"))
                .type(new TypeToken<Value<Double>>() {})
                .build();
    }

    @Listener
    public void onDataRegistration(GameRegistryEvent.Register<DataRegistration<?, ?>> event) {
        // Register custom data
        DataRegistration.builder()
                .dataClass(BlueprintData.class)
                .immutableClass(BlueprintData.Immutable.class)
                .builder(new BlueprintData.Builder())
                .id("blueprint")
                .name("Blueprint")
                .build();
    }

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        // Register custom CatalogType registry modules
        Sponge.getRegistry().registerModule(CraftingType.class, new CraftingTypesRegistry());
    }

    public BlueprintFacade getBlueprintFacade() {
        return components.blueprintFacade;
    }

    public ProfessionsMessagingFacade getMessagingFacade() {
        return components.messagingFacade;
    }

    private static class Components {
        @Inject
        AtherysProfessionsConfig config;

        @Inject
        BlueprintsConfig blueprintsConfig;

        @Inject
        ProfessionsMessagingFacade messagingFacade;

        @Inject
        BlueprintFacade blueprintFacade;

        @Inject
        CraftingListener craftingListener;
    }
}
