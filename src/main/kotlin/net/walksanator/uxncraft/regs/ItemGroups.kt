package net.walksanator.uxncraft.regs

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.Text


object ItemGroups {
    private val ITEM_GROUP: ItemGroup = FabricItemGroup.builder()
        .icon { ItemStack(Items.terminal) }
        .displayName(Text.translatable("itemGroup.tutorial.test_group"))
        .entries { context, entries ->
            entries.add(Items.terminal)
        }
        .build()
}