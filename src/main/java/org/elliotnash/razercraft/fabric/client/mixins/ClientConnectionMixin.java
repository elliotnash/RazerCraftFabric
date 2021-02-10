package org.elliotnash.razercraft.fabric.client.mixins;


import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.elliotnash.razercraft.fabric.client.events.DisconnectEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(at = @At("TAIL"), method = "disconnect")
    private void onDisconnect(Text disconnectReason, CallbackInfo ci){
        DisconnectEvent.EVENT.invoker().interact();
    }

}
