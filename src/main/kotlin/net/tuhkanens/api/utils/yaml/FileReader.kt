package net.tuhkanens.api.utils.yaml

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class FileReader(
    path: String,
    fileName: String
) {

    private val miniMessage: MiniMessage = MiniMessage.miniMessage()

    private val file: File = FileExtensions.getFile(path, fileName)
    private var yaml: YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    fun reload() {
        yaml = YamlConfiguration.loadConfiguration(file)
    }

    fun getString(key: String, def: String = ""): String = yaml.getString(key, def) ?: def
    fun getStringList(key: String) = yaml.getStringList(key)
    fun getInt(key: String, def: Int = 0): Int = yaml.getInt(key, def)
    fun getBoolean(key: String, def: Boolean = false): Boolean = yaml.getBoolean(key, def)
    fun getDouble(key: String, def: Double = 0.0): Double = yaml.getDouble(key, def)
    fun getFloat(key: String, def: Double = 0.0): Float = yaml.getDouble(key, def).toFloat()

    fun getComponent(key: String, vararg resolvers: TagResolver, def: String = ""): Component {
        return miniMessage.deserialize(yaml.getString(key) ?: def, *resolvers)
    }
    fun getComponentList(key: String, vararg resolvers: TagResolver, def: List<String> = emptyList()): Component {
        val list = yaml.getStringList(key).ifEmpty { def }
        return miniMessage.deserialize(list.joinToString("\n"), *resolvers)
    }
    fun hasValue(key: String, value: String): Boolean {
        val list = yaml.getStringList(key)
        return list.contains(value)
    }

    fun getSection(key: String): ConfigurationSection? = yaml.getConfigurationSection(key)

}