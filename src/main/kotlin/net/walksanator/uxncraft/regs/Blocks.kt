package net.walksanator.uxncraft.regs

import com.mojang.serialization.Decoder.Terminal
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import net.walksanator.uxncraft.Uxncraft.MOD_ID
import net.walksanator.uxncraft.blocks.TerminalBlock

object Blocks {
    lateinit var terminal: TerminalBlock
    fun register() {
        val reg = Registries.BLOCK
        terminal = Registry.register(reg, Identifier(MOD_ID,"terminal"),TerminalBlock(FabricBlockSettings.create()))

    }
}