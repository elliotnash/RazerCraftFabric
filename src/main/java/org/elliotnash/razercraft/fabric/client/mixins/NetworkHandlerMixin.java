package org.elliotnash.razercraft.fabric.client.mixins;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.HeldItemChangeS2CPacket;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.text.Text;
import org.elliotnash.razercraft.fabric.client.RazercraftfabricClient;
import org.elliotnash.razercraft.fabric.client.events.HotbarScrollEvent;
import org.elliotnash.razercraft.fabric.client.events.InventoryUpdateEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayNetworkHandler.class)
public class NetworkHandlerMixin {

//    @Inject(at = @At("TAIL"), method = "onScreenHandlerSlotUpdate")
//    private void inventoryUpdate(ScreenHandlerSlotUpdateS2CPacket packet, CallbackInfo ci){
//        if (RazercraftfabricClient.minecraft.player != null){
//            InventoryUpdateEvent.EVENT.invoker().interact(RazercraftfabricClient.minecraft.player.inventory);
//        }
//    }

    @Inject(at = @At("TAIL"), method = "onHeldItemChange")
    private void slotChange(HeldItemChangeS2CPacket packet, CallbackInfo ci){
        HotbarScrollEvent.EVENT.invoker().interact(packet.getSlot());
    }

}
