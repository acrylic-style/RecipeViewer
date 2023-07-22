package xyz.acrylicstyle.recipeviewer.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class RecipeGui implements InventoryHolder, Listener {
    private final ItemStack[] matrix;
    private final ItemStack result;

    public RecipeGui(ItemStack[] matrix, ItemStack result) {
        this.matrix = matrix;
        this.result = result;
    }

    @Override
    @NotNull
    public Inventory getInventory() {
        ItemStack b = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = b.getItemMeta();
        assert meta != null;
        meta.setDisplayName(" ");
        b.setItemMeta(meta);
        Inventory inventory = Bukkit.createInventory(this, 45, "Recipe");
        // -----
        inventory.setItem(0, b);
        inventory.setItem(1, b);
        inventory.setItem(2, b);
        inventory.setItem(3, b);
        inventory.setItem(4, b);
        inventory.setItem(5, b);
        inventory.setItem(6, b);
        inventory.setItem(7, b);
        inventory.setItem(8, b);
        // -----
        inventory.setItem(9, b);
        inventory.setItem(10, matrix[0]);
        inventory.setItem(11, matrix[1]);
        inventory.setItem(12, matrix[2]);
        inventory.setItem(13, b);
        inventory.setItem(14, b);
        inventory.setItem(15, b);
        inventory.setItem(16, b);
        inventory.setItem(17, b);
        // -----
        inventory.setItem(18, b);
        inventory.setItem(19, matrix[3]);
        inventory.setItem(20, matrix[4]);
        inventory.setItem(21, matrix[5]);
        inventory.setItem(22, b);
        inventory.setItem(23, b);
        inventory.setItem(24, result);
        inventory.setItem(25, b);
        inventory.setItem(26, b);
        // -----
        inventory.setItem(27, b);
        inventory.setItem(28, matrix[6]);
        inventory.setItem(29, matrix[7]);
        inventory.setItem(30, matrix[8]);
        inventory.setItem(31, b);
        inventory.setItem(32, b);
        inventory.setItem(33, b);
        inventory.setItem(34, b);
        inventory.setItem(35, b);
        // -----
        inventory.setItem(36, b);
        inventory.setItem(37, b);
        inventory.setItem(38, b);
        inventory.setItem(39, b);
        inventory.setItem(40, b);
        inventory.setItem(41, b);
        inventory.setItem(42, b);
        inventory.setItem(43, b);
        inventory.setItem(44, b);
        // -----
        return inventory;
    }

    public static class EventListener implements Listener {
        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
            if (!(e.getInventory().getHolder() instanceof RecipeGui)) return;
            e.setCancelled(true);
        }

        @EventHandler
        public void onInventoryDrag(InventoryDragEvent e) {
            if (!(e.getInventory().getHolder() instanceof RecipeGui)) return;
            e.setCancelled(true);
        }
    }
}
