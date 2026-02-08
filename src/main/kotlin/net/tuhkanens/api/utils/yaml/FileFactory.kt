package net.tuhkanens.api.utils.yaml

import net.tuhkanens.api.managers.DevAPI
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class FileFactory {

    private val plugin: JavaPlugin by lazy { DevAPI.getPlugin() }

    fun newFile(path: String, fileName: String) {

        val fullFileName = "$fileName.yml"
        val folder: File = FileExtensions.getFolder(plugin.dataFolder, path)

        if (!folder.exists()) {
            folder.mkdirs()
        }

        val file = File(folder, fullFileName)

        if (!file.exists()) {
            val resourcePath = if (path.isEmpty()) fullFileName else "$fileName/$fullFileName"
            try {
                plugin.saveResource(resourcePath, false)
            } catch (e: Exception) {
                file.createNewFile()
            }
        }

    }

    fun newFile(fileData: FileData) {
        this.newFile(fileData.path, fileData.fileName)
        FilesManager.register(fileData)
    }

    fun newFiles(vararg filesData: FileData) {
        for (fileData in filesData) {
            this.newFile(fileData)
        }

    }

}