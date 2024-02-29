package net.walksanator.uxncraft.varvara

import net.walksanator.uxncraft.Uxncraft
import net.walksanator.uxnkt.vm.varvara.VarvaraComputer
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
class McVarvaraComputer(ram: ByteArray, val id: Int) : VarvaraComputer(ram,1000) {

    val root: Path = Uxncraft.world_folder.resolve(id.toString()).normalize()

    init {
        file1.root = root
        file2.root = root
    }

    override fun run() {
        fuel = 1000
        super.run()
    }
}