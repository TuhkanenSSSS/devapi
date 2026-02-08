package net.tuhkanens.api.utils.yaml

import net.tuhkanens.api.managers.DevAPI
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class FileFactory {

    private val plugin: JavaPlugin by lazy { DevAPI.getPlugin() }

    fun newFile(fileData: FileData) {

        val fullFileName = "${fileData.fileName}.yml"

        val folder: File = FileExtensions.getFolder(plugin.dataFolder, fileData.path)

        if (!folder.exists()) {
            folder.mkdirs()
        }

        val file = File(folder, fullFileName)

        if (!file.exists()) {
            val resourcePath = if (fileData.path.isEmpty()) fullFileName else "${fileData.fileName}/$fullFileName"
            plugin.saveResource(resourcePath, false)
        }

    }

}