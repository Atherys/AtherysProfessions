package com.atherys.professions.listener;

import com.atherys.professions.AtherysProfessions;
import com.atherys.professions.AtherysProfessionsConfig;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.TickBlockEvent;

import java.util.Random;

public class GrowthListener {

    private static Random rand = new Random();

    @Listener
    public void onGrow(ChangeBlockEvent.Grow event) {
        event.getTransactions().forEach(transaction -> {
            transaction.getOriginal().getLocation().ifPresent(location -> {
                if (location.asHighestLocation().getPosition().equals(location.getPosition())) {
                    BlockType type = transaction.getOriginal().getState().getType();
                    double chance = AtherysProfessions.getInstance().getConfig().CROP_CHANCES.getOrDefault(type, 1.0);
                    double roll = rand.nextDouble();

                    if (roll > chance) {
                        transaction.setValid(false);
                    }
                } else {
                    transaction.setValid(false);
                }
            });
        });
    }
}
