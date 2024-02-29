package net.walksanator.uxncraft

import net.walksanator.uxncraft.blocks.TerminalEntity

interface Proxy {

    fun openTerminalScreen(te: TerminalEntity)

}

class ServerProxy : Proxy {

    override fun openTerminalScreen(te: TerminalEntity) {}

}