package com.example;

import com.example.modules.GlowModule;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class TemplateModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

		// Create keybind: G for Glow
		KeyMapping glowKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"key.templatemod.glow",        // ID
				InputConstants.Type.KEYSYM,     // Type input
				GLFW.GLFW_KEY_G,                // Key G
				"category.templatemod.cheats"   // Category in settings
		));

		// Every tick check
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (glowKey.consumeClick()) {
				GlowModule.toggle();

				// Option: message in chat
				if (client.player != null) {
					client.player.displayClientMessage(
							net.minecraft.network.chat.Component.literal(
									"Glow: " + (GlowModule.enabled ? "§aON" : "§cOFF")
							), true  // true = upper actionbar
					);
				}
			}
		});
	}
}