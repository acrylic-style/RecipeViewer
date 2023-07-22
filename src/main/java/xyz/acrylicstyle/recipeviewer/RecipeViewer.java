package xyz.acrylicstyle.recipeviewer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.acrylicstyle.recipeviewer.commands.ViewRecipeCommand;
import xyz.acrylicstyle.recipeviewer.gui.RecipeGui;

import java.util.Objects;

public class RecipeViewer extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new RecipeGui.EventListener(), this);
        Objects.requireNonNull(getCommand("viewrecipe")).setExecutor(new ViewRecipeCommand());
    }
}
