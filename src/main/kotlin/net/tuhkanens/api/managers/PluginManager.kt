package net.tuhkanens.api.managers

import org.bukkit.plugin.java.JavaPlugin

object PluginManager {

    lateinit var javaPlugin: JavaPlugin
        private set

    fun init(plugin: JavaPlugin) {

        this.javaPlugin = plugin
        this.dataFolder()

    }

    fun dataFolder() {
        if (!javaPlugin.dataFolder.exists()) {
            javaPlugin.dataFolder.mkdirs()
        }
    }

}