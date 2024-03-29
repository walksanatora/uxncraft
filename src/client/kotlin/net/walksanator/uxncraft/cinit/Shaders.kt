package net.walksanator.uxncraft.cinit

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceReloader
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import net.walksanator.uxncraft.Uxncraft.MOD_ID
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

object Shaders {

    private var screen = 0

    fun screen() = screen

    fun init() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(object :
            IdentifiableResourceReloadListener {

            override fun reload(s: ResourceReloader.Synchronizer, rm: ResourceManager, profiler: Profiler, profiler1: Profiler, executor: Executor, executor1: Executor): CompletableFuture<Void> {
                return CompletableFuture.runAsync({
                    if (screen != 0) GL30.glDeleteProgram(screen)

                    screen = loadShader(rm, "screen")
                }, executor1).thenCompose { s.whenPrepared(null) }
            }

            override fun getFabricId(): Identifier = Identifier(MOD_ID, "shaders")

        })
    }


    private fun loadShader(rm: ResourceManager, id: String): Int {
        val vshs = rm.getResource(Identifier(MOD_ID, "shaders/$id.vert")).use { it.inputStream.bufferedReader().readText() }
        val fshs = rm.getResource(Identifier(MOD_ID, "shaders/$id.frag")).use { it.inputStream.bufferedReader().readText() }

        val vsh = GL30.glCreateShader(GL30.GL_VERTEX_SHADER)
        val fsh = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER)
        val prog = GL30.glCreateProgram()

        // No goto? I'll make my own.
        run {
            GL30.glShaderSource(vsh, vshs)
            GL30.glShaderSource(fsh, fshs)

            GL30.glCompileShader(vsh)
            if (GL30.glGetShaderi(vsh, GL30.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
                // TODO use logger
                val log = GL30.glGetShaderInfoLog(vsh, 32768)
                println("Failed to compile vertex shader '$id'")
                for (line in log.lineSequence()) println(line)
                return@run
            }

            GL30.glCompileShader(fsh)
            if (GL30.glGetShaderi(fsh, GL30.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
                // TODO use logger
                val log = GL30.glGetShaderInfoLog(fsh, 32768)
                println("Failed to compile fragment shader '$id'")
                for (line in log.lineSequence()) println(line)
                return@run
            }

            GL30.glAttachShader(prog, vsh)
            GL30.glAttachShader(prog, fsh)
            GL30.glLinkProgram(prog)

            if (GL30.glGetProgrami(prog, GL30.GL_LINK_STATUS) == GL11.GL_FALSE) {
                // TODO use logger
                val log = GL30.glGetProgramInfoLog(prog, 32768)
                println("Failed to link program '$id'")
                for (line in log.lineSequence()) println(line)
                return@run
            }

            GL30.glDeleteShader(vsh)
            GL30.glDeleteShader(fsh)
            return prog
        }

        GL30.glDeleteShader(vsh)
        GL30.glDeleteShader(fsh)
        GL30.glDeleteProgram(prog)
        return 0
    }

}