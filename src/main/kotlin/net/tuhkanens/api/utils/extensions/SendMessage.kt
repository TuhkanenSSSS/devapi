package net.tuhkanens.api.utils.extensions

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit

object SendMessage {

    fun broadcast(message: Component, excluded: List<Any> = emptyList(), excludedConsole: Boolean = false) {
        Bukkit.getOnlinePlayers().forEach { player ->
            if (!excluded.contains(player)) {
                player.sendMessage(message)
            }
        }

        if (!excludedConsole) {
            Bukkit.getConsoleSender().sendMessage(message)
        }
    }

}