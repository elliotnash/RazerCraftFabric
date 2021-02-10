package org.elliotnash.razercraft.fabric.client.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface DisconnectEvent {

    Event<DisconnectEvent> EVENT = EventFactory.createArrayBacked(DisconnectEvent.class,
            (listeners) -> () -> {
                for (DisconnectEvent listener : listeners) {
                    ActionResult result = listener.interact();
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            }
    );

    ActionResult interact();

}
