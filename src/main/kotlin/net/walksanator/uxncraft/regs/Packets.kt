package net.walksanator.uxncraft.regs

import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import net.walksanator.uxncraft.Uxncraft.MOD_ID
import net.walksanator.uxncraft.blocks.TerminalEntity

object Packets {

    object Client {
    }

    object Server {
        val TERMINAL_KEY_TYPED = Identifier(MOD_ID, "terminal_key")
    }

    fun register() {
        ServerPlayNetworking.registerGlobalReceiver(Server.TERMINAL_KEY_TYPED, ::onKeyTypedTerminal)
    }
}

fun onKeyTypedTerminal(server: MinecraftServer, player: ServerPlayerEntity, handler: ServerPlayNetworkHandler, buf: PacketByteBuf, responseSender: PacketSender) {
    val pos = buf.readBlockPos()
    val k = buf.readByte()

    val dist = player.getCameraPosVec(1f).squaredDistanceTo(Vec3d.ofCenter(pos))
    if (dist > 10 * 10) return

    server.execute {
        val te = player.world.getBlockEntity(pos) as? TerminalEntity ?: return@execute
        te.pushKey(k)
    }
}