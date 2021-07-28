package com.atherys.professions.listener;


import com.atherys.professions.data.BlueprintKeys;
import com.atherys.professions.facade.BlueprintFacade;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;

@Singleton
public class CraftingListener {

    @Inject
    BlueprintFacade blueprintFacade;

    @Inject
    Logger logger;

    @Listener
    public void onRightClick(InteractItemEvent.Secondary event, @Root Player player) {
        event.getItemStack().get(BlueprintKeys.BLUEPRINT_ID).ifPresent(bpId -> {
            blueprintFacade.applyBlueprint(bpId, player);
        });
    }
        // Player places blueprint into crafting inventory ( crafting table, anvil, furnace, etc. ).

        // If they have the necessary items, the result will pop up where it would in normal crafting.

        // When they select the result to take it out of the crafting inventory,
        // the items required to craft the blueprint in their inventory will be removed

        // If they do not have the items, no result will show, and they will receive an informational message
        // to inform them of what they're missing still.

        // If the blueprint is an original, it is returned to their inventory.
        // If it is not, it is consumed along with the materials.

}
