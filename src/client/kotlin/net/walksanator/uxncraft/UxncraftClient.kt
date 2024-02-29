package net.walksanator.uxncraft

import net.fabricmc.api.ClientModInitializer

object UxncraftClient : ClientModInitializer {
	override fun onInitializeClient() {
		Uxncraft.proxy = ClientProxy()
	}
}