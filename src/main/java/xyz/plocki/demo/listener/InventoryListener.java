package xyz.plocki.demo.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import xyz.plocki.demo.command.ItemsCommand;

public class InventoryListener implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("Items")) {
            ItemsCommand.update.remove(event.getPlayer());
        }
    }
}
