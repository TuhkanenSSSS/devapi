package net.tuhkanens.api.utils.extensions

import net.tuhkanens.api.managers.DevAPI
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

object Scheduler {

    private val plugin: JavaPlugin by lazy { DevAPI.getPlugin() }

    fun sync(task: () -> Unit): BukkitTask {
        return Bukkit.getScheduler().runTask(plugin, Runnable { task() })
    }

    fun async(task: () -> Unit): BukkitTask {
        return Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable { task() })
    }

    fun later(delayTicks: Long, task: () -> Unit): BukkitTask {
        return Bukkit.getScheduler().runTaskLater(plugin, Runnable { task() }, delayTicks)
    }

    fun laterAsync(delayTicks: Long, task: () -> Unit): BukkitTask {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, Runnable { task() }, delayTicks)
    }

    fun timer(delayTicks: Long, periodTicks: Long, task: () -> Unit): BukkitTask {
        return Bukkit.getScheduler().runTaskTimer(plugin, Runnable { task() }, delayTicks, periodTicks)
    }

    fun timerAsync(delayTicks: Long, periodTicks: Long, task: () -> Unit): BukkitTask {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, Runnable { task() }, delayTicks, periodTicks)
    }

}