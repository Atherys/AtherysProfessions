package com.atherys.professions.listener;


import com.atherys.professions.data.BlueprintData;
import com.atherys.professions.data.BlueprintKeys;
import com.atherys.professions.facade.BlueprintFacade;
import com.atherys.professions.model.Blueprint;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.CraftItemEvent;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.InventoryProperty;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

@Singleton
public class CraftingListener {

    @Inject
    BlueprintFacade blueprintFacade;

    @Inject
    Logger logger;

    @Listener
    public void onCraft(ClickInventoryEvent event, @Root Player player) {
        logger.info("{} {} {}", player.toString(), event.getTransactions().toString(), event.getTargetInventory().toString());
        event.getTransactions().forEach(slotTransaction -> {
            ItemStackSnapshot itemStackSnapshot = slotTransaction.getFinal();

            itemStackSnapshot.get(BlueprintKeys.BLUEPRINT_ID).ifPresent(bpId -> {
                blueprintFacade.applyBlueprint(
                        bpId,
                        itemStackSnapshot.get(BlueprintKeys.IS_ORIGINAL).orElse(false),
                        itemStackSnapshot.get(BlueprintKeys.EFFICIENCY).orElse(0.0d),
                        player,
                        event.getTargetInventory(),
                        event
                );
            });
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
