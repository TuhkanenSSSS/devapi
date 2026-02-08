package net.tuhkanens.api.managers

import net.tuhkanens.api.utils.yaml.FileExtensions
import org.bukkit.plugin.java.JavaPlugin

object API {

    private var instance: JavaPlugin? = null

    internal fun setAPI(plugin: JavaPlugin) {

        if (this.instance != null) {
            throw IllegalStateException("API already initialized!")
        }

        this.instance = plugin

        this.initExtensions()

    }

    fun initExtensions() {
        FileExtensions.createDataFolder()
    }

    fun getPlugin(): JavaPlugin = get()

    private fun get(): JavaPlugin {
        return instance
            ?: throw IllegalStateException("API not initialized! Call API.setAPI(this) in onEnable()")
    }

}