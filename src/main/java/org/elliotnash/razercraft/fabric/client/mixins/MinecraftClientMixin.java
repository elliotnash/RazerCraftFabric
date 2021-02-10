package org.elliotnash.razercraft.fabric.client.mixins;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.GameOptions;
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


    int lastKey = -1;
    @Inject(at = @At("HEAD"), method = "handleInputEvents")
    private void onInput(CallbackInfo ci){
        for(int i = 0; i < 9; ++i) {
            if (this.options.keysHotbar[i].isPressed()) {
                if (i != lastKey){
                    lastKey = i;
                    //dispatch hotbar event :p
                    HotbarScrollEvent.EVENT.invoker().interact(i);
                }
            }
        }
    }

}
