package net.tuhkanens.api.utils.yaml

import net.tuhkanens.api.managers.PluginManager
import java.io.File

object FileExtensions {

    fun getFolder(dataFolder: File, path: String): File {
        return if (path.isEmpty()) dataFolder else File(dataFolder, path)
    }

    fun getFile(path: String, fileName: String): File {
        val folder: File = getFolder(PluginManager.javaPlugin.dataFolder, path)
        return File(folder, "$fileName.yml")
    }

}