package net.walksanator.uxncraft.regs

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.walksanator.uxncraft.Uxncraft.MOD_ID

object Items {
    lateinit var terminal: BlockItem

    fun register() {
        val reg = Registries.ITEM
        terminal = Registry.register(reg, Identifier(MOD_ID,"terminal"), BlockItem(Blocks.terminal,FabricItemSettings()))
    }

}