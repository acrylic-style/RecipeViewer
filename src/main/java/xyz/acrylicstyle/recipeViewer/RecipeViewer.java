package xyz.acrylicstyle.recipeViewer;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.acrylicstyle.recipeViewer.commands.ViewRecipeCommand;
import xyz.acrylicstyle.tomeito_api.TomeitoAPI;

public class RecipeViewer extends JavaPlugin {
    public static RecipeViewer instance = null;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        TomeitoAPI.registerCommand("viewrecipe", new ViewRecipeCommand());
    }
}
