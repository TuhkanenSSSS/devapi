package net.tuhkanens.api.utils.yaml

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class FileWriter(
    fileData: FileData
) {

    private val file: File = FileExtensions.getFile(fileData.path, fileData.fileName)
    private var yaml: YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    fun reload() {
        yaml = YamlConfiguration.loadConfiguration(file)
    }

    fun set(key: String, value: Any) {
        yaml.set(key, value)
        this.save()
    }

    fun add(key: String, value: Any) {

        val list = yaml.getList(key)?.toMutableList() ?: mutableListOf()

        list.add(value)
        yaml.set(key, list)

        this.save()

    }

    fun remove(key: String, value: Any) {

        val list = yaml.getList(key)?.toMutableList() ?: mutableListOf()

        list.remove(value)
        yaml.set(key, list)

        this.save()

    }

    private fun save() {
        try {
            yaml.save(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}