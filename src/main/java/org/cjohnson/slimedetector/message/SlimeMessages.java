package org.cjohnson.slimedetector.message;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public abstract class SlimeMessages {
    public static void sendDisabledMessage(Player player) {
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

    public static void sendEnabledMessage(Player player) {
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
