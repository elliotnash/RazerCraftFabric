package org.elliotnash.razercraft.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import org.elliotnash.razercraft.fabric.client.events.HotbarScrollEvent;
import org.elliotnash.razercraft.fabric.client.events.InventoryUpdateEvent;

@Environment(EnvType.CLIENT)
public class RazercraftfabricClient implements ClientModInitializer {
    public static MinecraftClient minecraft;
    @Override
    public void onInitializeClient() {
        System.out.println("CHEESY BOY STARTING UP");
        minecraft = MinecraftClient.getInstance();


        //events
        HotbarScrollEvent.EVENT.register((newSlot -> {

            System.out.println("hot bar scrolled");

            return ActionResult.PASS;

        }));

        InventoryUpdateEvent.EVENT.register((inventory -> {

            System.out.println("Inventory updated");

            return ActionResult.PASS;

        }));


    }

}
