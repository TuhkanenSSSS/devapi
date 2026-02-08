package net.tuhkanens.api.utils.yaml

import net.tuhkanens.api.managers.PluginManager
import java.io.File

class FileFactory {

    fun newFile(path: String, fileName: String) {

        val fullFileName = "$fileName.yml"

        val folder: File = FileExtensions.getFolder(PluginManager.javaPlugin.dataFolder, path)

        if (!folder.exists()) {
            folder.mkdirs()
        }

        val file = File(folder, fullFileName)

        if (!file.exists()) {
            val resourcePath = if (path.isEmpty()) fullFileName else "$path/$fullFileName"
            PluginManager.javaPlugin.saveResource(resourcePath, false)
        }

    }

}