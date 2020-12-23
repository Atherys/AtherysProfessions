package com.atherys.professions.facade;

import com.atherys.professions.api.CraftingType;
import com.atherys.professions.api.exception.ProfessionsCommandException;
import com.atherys.professions.config.BlueprintsConfig;
import com.atherys.professions.data.BlueprintData;
import com.atherys.professions.model.Blueprint;
import com.atherys.professions.model.BlueprintIngredient;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.tileentity.carrier.BrewingStand;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.type.Profession;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.CraftItemEvent;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.crafting.CraftingInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class BlueprintFacade {

    @Inject
    private BlueprintsConfig blueprintsConfig;

    @Inject
    private ProfessionsMessagingFacade messagingFacade;

    @Inject
    private Logger logger;

    private Map<String, Blueprint> blueprints = new HashMap<>();

    public void init() {
        blueprints.clear();

        blueprintsConfig.BLUEPRINTS.forEach(bc -> {
            Blueprint bp = new Blueprint();
            bp.setId(bc.getId());
            bp.setPermission(bc.getPermission());
            bp.setCraftingType(bc.getCraftingType());
            bp.setIngredients(
                    bc.getIngredients().stream().map(bic -> {
                        BlueprintIngredient blueprintIngredient = new BlueprintIngredient();
                        blueprintIngredient.setAcceptableItems(bic.getAcceptableItems());
                        return blueprintIngredient;
                    }).collect(Collectors.toList())
            );
            bp.setResult(bc.getResult());

            blueprints.put(bc.getId(), bp);
        });
    }

    public void addBlueprintDataToHeldItem(Player src, String blueprintId, boolean isOriginal, double efficiency) throws ProfessionsCommandException {
        Optional<ItemStack> equipped = src.getEquipped(EquipmentTypes.MAIN_HAND);

        if (!equipped.isPresent()) {
            throw new ProfessionsCommandException("You are not holding any item!");
        }

        if (!equipped.get().supports(BlueprintData.class)) {
            throw new ProfessionsCommandException("The item you are holding does not support blueprint data.");
        }

        if (!blueprints.containsKey(blueprintId)) {
            throw new ProfessionsCommandException("No such blueprint is configured");
        }

        BlueprintData data = new BlueprintData(isOriginal, blueprintId, efficiency);

        equipped.get().offer(data).ifNotSuccessful(() -> new ProfessionsCommandException("Unable to add blueprint data to item."));
    }

    public void applyBlueprint(String blueprintId, Boolean isOriginal, Double efficiency, Player player, Inventory targetInventory, ClickInventoryEvent event) {
        Blueprint bp = blueprints.get(blueprintId);

        if (bp == null) {
            return;
        }

        boolean isCorrectInventoryType = targetInventory.getArchetype().equals(bp.getCraftingType().getInventoryArchetype());

        boolean isInventoryOfCraftingType = Sponge.getRegistry().getAllOf(CraftingType.class).stream()
                .anyMatch(ct -> ct.getInventoryArchetype().equals(targetInventory.getArchetype()));

        // If this is not the correct type of inventory for this blueprint, and is of another crafting inventory type, cancel the transfer event
        // otherwise, if it's just not the correct type, but not of another crafting type, permit the transfer event, but don't craft ( to allow for chests, hoppers, etc. )
        if (!isCorrectInventoryType && isInventoryOfCraftingType) {
            messagingFacade.error(player, "This blueprint is meant to be used in a(n) ", bp.getCraftingType().getName());
            event.setCancelled(true);
            return;
        } else if (!isCorrectInventoryType) {
            return;
        }

        // TODO: Calculate ingredients,
        //  check for their existence in the player's inventory,
        //  subtract the necessary amounts when the player selects to craft the blueprint's result ( clicks on the product in the inventory screen )

        messagingFacade.info(player, "This will craft a ", bp.getResult().toString());

        ItemStackSnapshot result = bp.getResult();
        ItemStack stack = result.createStack();

        player.getInventory().offer(stack);
    }
}
