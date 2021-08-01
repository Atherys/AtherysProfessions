package com.atherys.professions.listener;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;

public class GrowthListener {

    @Listener
    public void onGrow(ChangeBlockEvent.Grow event) {
        event.getTransactions().forEach(transaction -> {
            transaction.getOriginal().getLocation().ifPresent(location -> {
                if (!location.asHighestLocation().getPosition().equals(location.getPosition())) {
                    transaction.setValid(false);
                }
            });
        });
    }
}
