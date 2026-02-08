package net.tuhkanens.api.utils.yaml

import net.tuhkanens.api.managers.DevAPI
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

object FileExtensions {

    private val plugin: JavaPlugin by lazy { DevAPI.getPlugin() }

    fun createDataFolder() {
        if (!plugin.dataFolder.exists()) {
            plugin.dataFolder.mkdirs()
        }
    }

    fun getFolder(dataFolder: File, path: String): File {
        return if (path.isEmpty()) dataFolder else File(dataFolder, path)
    }

    fun getFile(path: String, fileName: String): File {
        val folder: File = getFolder(plugin.dataFolder, path)
        return File(folder, "$fileName.yml")
    }

}