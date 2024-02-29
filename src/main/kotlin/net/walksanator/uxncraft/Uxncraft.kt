package net.walksanator.uxncraft

import net.fabricmc.api.EnvType
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.WorldSavePath
import net.walksanator.uxncraft.regs.*
import org.slf4j.LoggerFactory
import java.nio.file.Path

object Uxncraft : ModInitializer {
    private val logger = LoggerFactory.getLogger("uxncraft")
	lateinit var world_folder: Path
	val MOD_ID = "uxncraft"

	lateinit var proxy: Proxy

	var resources: Resources? = null

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")
		ServerLifecycleEvents.SERVER_STARTED.register {
			world_folder = it.getSavePath(WorldSavePath.ROOT).resolve("uxn").normalize()
		}

		if (FabricLoader.getInstance().environmentType!! == EnvType.SERVER) {
			proxy = ServerProxy()
		}

		Blocks.register()
		BlockEntityTypes.register()
		Items.register()
		Packets.register()
		Resources.register()
	}
}