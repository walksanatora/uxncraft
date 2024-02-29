package net.walksanator.uxncraft


import net.minecraft.client.MinecraftClient
import net.walksanator.uxncraft.blocks.TerminalEntity
import net.walksanator.uxncraft.screen.TerminalScreen

class ClientProxy : Proxy {

    val mc = MinecraftClient.getInstance()

    override fun openTerminalScreen(te: TerminalEntity) {
        mc.setScreen(TerminalScreen(te))
    }

}