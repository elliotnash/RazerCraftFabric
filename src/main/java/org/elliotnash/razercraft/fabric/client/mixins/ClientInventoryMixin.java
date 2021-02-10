package org.elliotnash.razercraft.fabric.client.mixins;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.elliotnash.razercraft.fabric.client.events.HotbarScrollEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(PlayerInventory.class)
public class ClientInventoryMixin {

    @Shadow public int selectedSlot;

    @Inject(at = @At("TAIL"), method = "scrollInHotbar")
    private void dropItem(double scrollAmount, CallbackInfo ci){
        HotbarScrollEvent.EVENT.invoker().interact(selectedSlot);
    }

}
