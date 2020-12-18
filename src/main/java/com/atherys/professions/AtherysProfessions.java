package com.atherys.professions;

import com.atherys.core.event.AtherysHibernateInitializedEvent;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.item.recipe.crafting.CraftingRecipes;
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

    private void init() {
        instance = this;

        components = new Components();

        professionsInjector = injector.createChildInjector();
        professionsInjector.injectMembers(components);

        init = true;
    }

    private void start() {

    }

    private void stop() {

    }

    private void reload() {

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

    private static class Components {

    }
}
