package net.walksanator.uxncraft.regs

import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import net.walksanator.uxncraft.Uxncraft
import net.walksanator.uxncraft.Uxncraft.MOD_ID
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

@JvmRecord
data class Resources(
    val bootloader: ByteArray,
    val charset: ByteArray,
) {
    companion object {
        private val ID = Identifier(MOD_ID, "data")

        fun register() {
            ResourceManagerHelper.get(ResourceType.SERVER_DATA)
                .registerReloadListener(object : SimpleResourceReloadListener<Resources> {
                    override fun load(
                        manager: ResourceManager,
                        profiler: Profiler,
                        executor: Executor
                    ): CompletableFuture<Resources> {
                        return CompletableFuture.supplyAsync({
                            val bootloader = loadImage(manager, "bootldr.bin")
                            val charset = loadImage(manager, "charset.bin")
                            Resources(bootloader, charset)
                        }, executor)
                    }

                    override fun apply(
                        data: Resources,
                        manager: ResourceManager,
                        profiler: Profiler,
                        executor: Executor
                    ): CompletableFuture<Void> {
                        return CompletableFuture.runAsync({
                            Uxncraft.resources = data
                        }, executor)
                    }

                    override fun getFabricId(): Identifier {
                        return ID
                    }
                })
        }

        private fun loadImage(manager: ResourceManager, name: String): ByteArray {
            try {
                manager.getResource(Identifier(MOD_ID, name)).let { res ->
                    return res.getOrNull()!!.inputStream.readAllBytes()
                }
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }
}