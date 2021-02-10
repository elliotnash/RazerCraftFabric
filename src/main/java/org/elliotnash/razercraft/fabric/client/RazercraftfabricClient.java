package org.elliotnash.razercraft.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import org.elliotnash.razercraft.core.RazerController;
import org.elliotnash.razercraft.fabric.client.events.HotbarScrollEvent;
import org.elliotnash.razercraft.fabric.client.events.InventoryUpdateEvent;

@Environment(EnvType.CLIENT)
public class RazercraftfabricClient implements ClientModInitializer {
    public static MinecraftClient minecraft;
    public static RazerController controller;
    @Override
    public void onInitializeClient() {

        //TODO block drops aren't detected if inventory screen is open and dropped with click

        minecraft = MinecraftClient.getInstance();

        controller = new RazerController(10);

        //events
        HotbarScrollEvent.EVENT.register((newSlot -> {

            controller.activeSlot = newSlot;
            controller.draw();

            return ActionResult.PASS;

        }));

        InventoryUpdateEvent.EVENT.register((inventory -> {

            controller.filledSlots.clear();
            for (int i = 0; i < 9; i++){
                if ( minecraft.player != null && !minecraft.player.inventory.getStack(i).isEmpty()){
                    //stack isn't empty, add it to filled slots
                    controller.filledSlots.add(i);
                }
            }
            controller.draw();

            return ActionResult.PASS;

        }));





    }

}
