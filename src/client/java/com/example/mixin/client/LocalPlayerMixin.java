package com.example.mixin.client;

import com.example.modules.SprintModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;

        if (SprintModule.isEnabled()) {
            boolean isMovingForward = Minecraft.getInstance().options.keyUp.isDown();

            if (isMovingForward && !player.isShiftKeyDown() && !player.isSwimming()) {
                player.setSprinting(true);
            }
        }
    }
}