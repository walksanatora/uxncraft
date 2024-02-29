package net.walksanator.uxncraft.regs

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.walksanator.uxncraft.Uxncraft.MOD_ID
import net.walksanator.uxncraft.blocks.TerminalEntity

object BlockEntityTypes {
    private val reg = Registries.BLOCK_ENTITY_TYPE

    lateinit var terminal: BlockEntityType<TerminalEntity>

    fun register() {
        val reg = Registries.BLOCK_ENTITY_TYPE
        terminal = Registry.register(reg, Identifier(MOD_ID,"terminal"),
            FabricBlockEntityTypeBuilder.create(::TerminalEntity,Blocks.terminal).build()
        )
    }
}