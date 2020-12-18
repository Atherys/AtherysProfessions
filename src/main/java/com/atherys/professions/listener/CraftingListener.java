package com.atherys.professions.listener;


import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;

public class CraftingListener {

    @Listener
    public void onInventoryMove(ChangeInventoryEvent.Transfer event) {
        // Player places blueprint into crafting inventory ( crafting table, anvil, furnace, etc. ).

        // If they have the necessary items, the result will pop up where it would in normal crafting.

        // When they select the result to take it out of the crafting inventory,
        // the items required to craft the blueprint in their inventory will be removed

        // If they do not have the items, no result will show, and they will receive an informational message
        // to inform them of what they're missing still.

        // If the blueprint is an original, it is returned to their inventory.
        // If it is not, it is consumed along with the materials.
    }

}
