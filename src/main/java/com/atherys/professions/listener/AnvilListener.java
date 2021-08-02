package com.atherys.professions.listener;

import com.atherys.professions.AtherysProfessions;
import com.atherys.rpg.AtherysRPG;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.property.item.UseLimitProperty;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.UpdateAnvilEvent;
import org.spongepowered.api.item.inventory.AnvilCost;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.transform.TextFormatter;

public class AnvilListener {
    @Listener
    public void onAnvilUpdate(UpdateAnvilEvent event) {
        ItemStackSnapshot result = event.getResult().getDefault();

        result.get(Keys.ITEM_DURABILITY).ifPresent(durability -> {
            ItemStack item = result.createStack();

            int originalDurability = event.getLeft().get(Keys.ITEM_DURABILITY).get();
            int maxDurability = item.getProperty(UseLimitProperty.class).get().getValue();
            double per = AtherysProfessions.getInstance().getRepairsConfig().REPAIR_PERCENTS.getOrDefault(item.getType(), 0.25);
            int amount = event.getRight().getQuantity();
            item.offer(Keys.ITEM_DURABILITY, originalDurability + (int) (maxDurability * per * amount));

            Text name = TextSerializers.formattingCode('&').deserialize(event.getItemName());
            item.offer(Keys.DISPLAY_NAME, name);

            event.getResult().setCustom(item.createSnapshot());

            AnvilCost cost = event.getCosts().getDefault();
            AnvilCost free = cost.withLevelCost(0);

            event.getCosts().setCustom(free);
        });
    }
}
