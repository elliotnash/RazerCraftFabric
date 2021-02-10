package org.elliotnash.razercraft.fabric.client.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.ActionResult;

public interface HotbarScrollEvent {

    Event<HotbarScrollEvent> EVENT = EventFactory.createArrayBacked(HotbarScrollEvent.class,
            (listeners) -> (newSlot) -> {
                for (HotbarScrollEvent listener : listeners) {
                    ActionResult result = listener.interact(newSlot);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            }
    );

    ActionResult interact(int newSlot);

}

