package com.atherys.professions.facade;

import com.atherys.professions.api.exception.ProfessionsCommandException;
import com.atherys.professions.config.BlueprintsConfig;
import com.atherys.professions.data.BlueprintData;
import com.atherys.professions.model.Blueprint;
import com.atherys.professions.service.RecipeService;
import com.atherys.rpg.AtherysRPG;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.spongepowered.api.data.type.HandType;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class BlueprintFacade {

    @Inject
    private BlueprintsConfig blueprintsConfig;

    @Inject
    private ProfessionsMessagingFacade messagingFacade;

    @Inject
    private RecipeService recipeService;

    private Map<String, Blueprint> blueprints = new HashMap<>();

    public void init() {
        blueprints.clear();

        blueprintsConfig.BLUEPRINTS.forEach(bc -> {
            Blueprint bp = new Blueprint();
            bp.setIngredients(
                    bc.RECIPE.INGREDIENTS.stream()
                            .map(recipeService::getStackForItemConfig)
                            .collect(Collectors.toList())
            );
            bp.setResult(recipeService.getStackForItemConfig(bc.RECIPE.RESULT));

            blueprints.put(bc.RECIPE.ID, bp);

            Map<String, ItemStackSnapshot> rpgItems = AtherysRPG.getInstance().getItemFacade().getCachedItems();
            if (bc.RPG_ITEM != null && !bc.RPG_ITEM.isEmpty()) {
                ItemStack item = rpgItems.get(bc.RPG_ITEM).createStack();
                item.offer(new BlueprintData(true, bc.RECIPE.ID, 0.0));
                AtherysRPG.getInstance().getItemFacade().registerItem(bc.RPG_ITEM, item.createSnapshot());
            }
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

    public void applyBlueprint(String blueprintId, Player player, HandType handType) {
        Blueprint bp = blueprints.get(blueprintId);

        if (bp == null) {
            return;
        }

        Inventory inventory = player.getInventory();
        for (ItemStackSnapshot ingredient : bp.getIngredients()) {
            if (!inventory.contains(ingredient.createStack())) {
                player.sendMessage(new ProfessionsCommandException((Text.of("You are missing ingredients for this blueprint."))).getText());
                return;
            }
        }

        bp.getIngredients().forEach(itemStackSnapshot -> {
            inventory.query(QueryOperationTypes.ITEM_STACK_IGNORE_QUANTITY.of(itemStackSnapshot.createStack())).poll(itemStackSnapshot.getQuantity());
        });

        ItemStackSnapshot result = bp.getResult();
        ItemStack stack = result.createStack();

        player.setItemInHand(handType, stack);
    }
}
