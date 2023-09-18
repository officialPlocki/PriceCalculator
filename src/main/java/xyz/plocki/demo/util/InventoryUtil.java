package xyz.plocki.demo.util;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.plocki.demo.command.ItemsCommand;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class InventoryUtil {

    private final Inventory inventory = Bukkit.createInventory(null, 27, "Items");

    public InventoryUtil() throws IOException {
        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§f ");
        glass.setItemMeta(glassMeta);
        for (int i = 0; i < this.inventory.getSize(); i++) {
            this.inventory.setItem(i, glass);
        }
        HashMap<String, HashMap<String, String>> strings = new ResponseUtil().getWebResponse();
        for (int i = 0; i < strings.keySet().size(); i++) {
            ItemStack exampleItem = new ItemStack(Material.STONE);
            ItemMeta exampleMeta = exampleItem.getItemMeta();
            HashMap<String, String> val = strings.get(new ArrayList<>(strings.keySet()).get(i));
            exampleMeta.setDisplayName(val.get("name"));
            ArrayList<String> lore = Lists.newArrayList("", "§7Preis: §e" + new DecimalFormat("#,###.##").format(Double.parseDouble(val.get("price"))), "");
            exampleMeta.setLore(lore);
            exampleItem.setItemMeta(exampleMeta);
            this.inventory.setItem(i, exampleItem);
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("Demo")), () -> {
            try {
                this.update();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Inventory inv = this.inventory;
            ItemsCommand.update.forEach(player -> {
                for (int i = 0; i < inv.getContents().length; i++) {
                    player.getOpenInventory().setItem(i, inv.getContents()[i]);
                }
            });
        }, 0L, 80L);
    }

    public Inventory getInventory() {
        return inventory;
    }

    private void update() throws IOException {
        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        assert (glassMeta != null);
        glassMeta.setDisplayName("§f ");
        glass.setItemMeta(glassMeta);
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, glass);
        }
        HashMap<String, HashMap<String, String>> strings = new ResponseUtil().getWebResponse();
        for (int i = 0; i < strings.keySet().size(); i++) {
            ItemStack exampleItem = new ItemStack(Material.STONE);
            ItemMeta exampleMeta = exampleItem.getItemMeta();
            HashMap<String, String> val = strings.get(new ArrayList<>(strings.keySet()).get(i));
            assert (exampleMeta != null);
            exampleMeta.setDisplayName(val.get("name"));
            ArrayList<String> lore = Lists.newArrayList("", "§7Preis: §e" + new DecimalFormat("#,###.##").format(Double.parseDouble(val.get("price"))), "");
            exampleMeta.setLore(lore);
            exampleItem.setItemMeta(exampleMeta);
            inventory.setItem(i, exampleItem);
        }
    }
}