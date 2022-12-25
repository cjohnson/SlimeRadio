package org.cjohnson.slimedetector.world;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Chunk;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.TextComponent;

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
            disabledMessage(player);

            return;
        }
        subscribers.add(player);
        enabledMessage(player);
    }

    private static void disabledMessage(Player player) {
        BaseComponent message = new TextComponent();

        TextComponent openBracket = new TextComponent("[");
        openBracket.setColor(ChatColor.DARK_GRAY);
        message.addExtra(openBracket);

        TextComponent off = new TextComponent("OFF");
        off.setColor(ChatColor.RED);
        off.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/slime"));
        message.addExtra(off);

        TextComponent closedBracket = new TextComponent("] ");
        closedBracket.setColor(ChatColor.DARK_GRAY);
        message.addExtra(closedBracket);

        TextComponent extraMessage = new TextComponent("Slime Radio is disabled.");
        extraMessage.setColor(ChatColor.GRAY);
        message.addExtra(extraMessage);

        player.spigot().sendMessage(message);
    }

    private static void enabledMessage(Player player) {
        BaseComponent message = new TextComponent();

        TextComponent openBracket = new TextComponent("[");
        openBracket.setColor(ChatColor.DARK_GRAY);
        message.addExtra(openBracket);

        TextComponent off = new TextComponent("ON");
        off.setColor(ChatColor.DARK_GREEN);
        off.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/slime"));
        message.addExtra(off);

        TextComponent closedBracket = new TextComponent("] ");
        closedBracket.setColor(ChatColor.DARK_GRAY);
        message.addExtra(closedBracket);

        TextComponent extraMessage = new TextComponent("Slime Radio is enabled.");
        extraMessage.setColor(ChatColor.GRAY);
        message.addExtra(extraMessage);

        player.spigot().sendMessage(message);
    }
}
