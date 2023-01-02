package org.cjohnson.slimedetector.message;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SlimeMessages {
    private static BaseComponent disabledMessage;
    private static BaseComponent enabledMesssage;
    private static BaseComponent nonPlayerMessage;

    public static void sendDisabledMessage(Player player) {
        if(disabledMessage != null) {
            player.spigot().sendMessage(disabledMessage);
            return;
        }

        disabledMessage = new TextComponent();

        TextComponent openBracket = new TextComponent("[");
        openBracket.setColor(ChatColor.DARK_GRAY);
        disabledMessage.addExtra(openBracket);

        TextComponent off = new TextComponent("OFF");
        off.setColor(ChatColor.RED);
        off.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/slime"));
        disabledMessage.addExtra(off);

        TextComponent closedBracket = new TextComponent("] ");
        closedBracket.setColor(ChatColor.DARK_GRAY);
        disabledMessage.addExtra(closedBracket);

        TextComponent extraMessage = new TextComponent("Slime Radio is disabled.");
        extraMessage.setColor(ChatColor.GRAY);
        disabledMessage.addExtra(extraMessage);

        player.spigot().sendMessage(disabledMessage);
    }

    public static void sendEnabledMessage(Player player) {
        if(enabledMesssage != null) {
            player.spigot().sendMessage(enabledMesssage);
            return;
        }

        enabledMesssage = new TextComponent();

        TextComponent openBracket = new TextComponent("[");
        openBracket.setColor(ChatColor.DARK_GRAY);
        enabledMesssage.addExtra(openBracket);

        TextComponent off = new TextComponent("ON");
        off.setColor(ChatColor.DARK_GREEN);
        off.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/slime"));
        enabledMesssage.addExtra(off);

        TextComponent closedBracket = new TextComponent("] ");
        closedBracket.setColor(ChatColor.DARK_GRAY);
        enabledMesssage.addExtra(closedBracket);

        TextComponent extraMessage = new TextComponent("Slime Radio is enabled.");
        extraMessage.setColor(ChatColor.GRAY);
        enabledMesssage.addExtra(extraMessage);

        player.spigot().sendMessage(enabledMesssage);
    }

    public static void sendNonPlayerMessage(CommandSender sender) {
        if(nonPlayerMessage != null) {
            sender.spigot().sendMessage(nonPlayerMessage);
            return;
        }

        nonPlayerMessage = new TextComponent("The slime command is not supported for non-players.");
        nonPlayerMessage.setColor(ChatColor.RED);

        sender.spigot().sendMessage(nonPlayerMessage);
    }
}
