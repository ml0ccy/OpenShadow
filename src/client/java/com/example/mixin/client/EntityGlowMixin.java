package com.example.mixin.client;

import com.example.modules.GlowModule;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityGlowMixin {

    @Inject(method = "isCurrentlyGlowing", at = @At("HEAD"), cancellable = true)
    private void onIsCurrentlyGlowing(CallbackInfoReturnable<Boolean> cir) {
        if (!GlowModule.enabled) return;

        Entity self = (Entity) (Object) this;

        // Dont glow yourself
        if (self == Minecraft.getInstance().player) return;

        cir.setReturnValue(true);
    }
}