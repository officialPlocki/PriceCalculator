package xyz.plocki.demo.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.plocki.demo.Demo;

import java.util.ArrayList;
import java.util.List;

public class ItemsCommand implements CommandExecutor {

    public static final List<Player> update = new ArrayList<>();

    public boolean onCommand(@NotNull CommandSender commandSender, Command command, @NotNull String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.openInventory(Demo.util.getInventory());
            update.add(player);
        }
        return false;
    }
}
