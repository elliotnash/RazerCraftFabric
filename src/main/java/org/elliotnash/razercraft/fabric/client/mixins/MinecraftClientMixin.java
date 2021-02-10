package org.elliotnash.razercraft.fabric.client.mixins;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.GameOptions;
import org.elliotnash.razercraft.fabric.client.RazercraftfabricClient;
import org.elliotnash.razercraft.fabric.client.events.HotbarScrollEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow @Final public GameOptions options;


    boolean[] pressed = new boolean[9];
    @Inject(at = @At("TAIL"), method = "handleInputEvents")
    private void onInput(CallbackInfo ci){
        for(int i = 0; i < 9; ++i) {
            if (this.options.keysHotbar[i].isPressed()) {
                if (!pressed[i]){
                    pressed[i] = true;
                    //dispatch hotbar event :p
                    HotbarScrollEvent.EVENT.invoker().interact(RazercraftfabricClient.minecraft.player.inventory.selectedSlot);
                }
            } else {
                pressed[i] = false;
            }
        }
    }

}
