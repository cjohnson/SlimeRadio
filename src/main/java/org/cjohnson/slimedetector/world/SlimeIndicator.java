package org.cjohnson.slimedetector.world;

import org.bukkit.Chunk;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.cjohnson.slimedetector.message.SlimeMessages;

import java.util.ArrayList;

public class SlimeIndicator {
    private ArrayList<Player> subscribers = new ArrayList<>();
    private SlimeRadio radio = new SlimeRadio(2);

    public void indicate() {
        for (Player player : subscribers) {
            radio.sample(player.getLocation());
            ArrayList<Chunk> slimeChunks = radio.getSlimeChunks();

            for (Chunk slimeChunk : slimeChunks) {
                renderIndicatorHalo(player, slimeChunk.getX(), player.getLocation().getBlockY(), slimeChunk.getZ());
            }
        }
    }

    public void renderIndicatorHalo(Player player, int chunkX, int worldY, int chunkZ) {
        int worldX = (chunkX << 4) + 8;
        int worldZ = (chunkZ << 4) + 8;

        int t = 24;
        for(int dt = 0; dt < t; dt++) {
            double ddt = (2d * Math.PI) * ((double)dt/(double)t);

            renderParticle(player, (int)(worldX + (5 * Math.cos(ddt))), worldY + 5, (int)(worldZ + (5 * Math.sin(ddt))));
        }
    }

    public void renderParticle(Player player, int x, int y, int z) {
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GREEN, 5);
        player.spawnParticle(Particle.REDSTONE, x, y, z, 1, dustOptions);
    }

    public void toggle(Player player) {
        if(subscribers.contains(player)) {
            subscribers.remove(player);
            SlimeMessages.sendDisabledMessage(player);

            return;
        }
        subscribers.add(player);
        SlimeMessages.sendEnabledMessage(player);
    }
}
