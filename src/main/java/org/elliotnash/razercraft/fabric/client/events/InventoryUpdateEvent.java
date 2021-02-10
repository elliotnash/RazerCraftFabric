package org.elliotnash.razercraft.fabric.client.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.ActionResult;

public interface InventoryUpdateEvent {

    Event<InventoryUpdateEvent> EVENT = EventFactory.createArrayBacked(InventoryUpdateEvent.class,
            (listeners) -> (inventory) -> {
                for (InventoryUpdateEvent listener : listeners) {
                    ActionResult result = listener.interact(inventory);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            }
    );

    ActionResult interact(Inventory inventory);

}
