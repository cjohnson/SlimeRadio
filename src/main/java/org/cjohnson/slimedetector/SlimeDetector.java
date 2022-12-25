package org.cjohnson.slimedetector;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.cjohnson.slimedetector.chunk.SlimeCommand;
import org.cjohnson.slimedetector.world.SlimeIndicator;

@SuppressWarnings("unused")
public final class SlimeDetector extends JavaPlugin {
    private SlimeIndicator indicator;
    private BukkitTask task;

    @Override
    public void onEnable() {
        indicator = new SlimeIndicator();
        getCommand("slime").setExecutor(new SlimeCommand(indicator));

        task = getServer().getScheduler().runTaskTimer(this, indicator::indicate, 0L, 20L);
    }

    @Override
    public void onDisable() {
        task.cancel();
    }
}
