package xyz.plocki.demo;

import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.plocki.demo.command.ItemsCommand;
import xyz.plocki.demo.listener.InventoryListener;
import xyz.plocki.demo.util.InventoryUtil;

public class Demo extends JavaPlugin {

    public static final String webAddress = "http://localhost:26/api";
    public static InventoryUtil util;

    @Override
    public void onEnable() {
        try {
            util = new InventoryUtil();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.getCommand("items").setExecutor(new ItemsCommand());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
    }

}
