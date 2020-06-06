package xyz.acrylicstyle.recipeViewer.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Keyed;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.CollectionList;
import xyz.acrylicstyle.recipeViewer.gui.RecipeGui;
import xyz.acrylicstyle.tomeito_api.command.PlayerCommandExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ViewRecipeCommand extends PlayerCommandExecutor implements TabCompleter {
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "/viewrecipe <namespaced id>");
            return;
        }
        CollectionList<Recipe> recipes = new CollectionList<>();
        Bukkit.recipeIterator().forEachRemaining(recipes::add);
        recipes
                .filter(recipe -> recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe)
                .map(r -> (Keyed) r)
                .filter(k -> k.getKey().toString().equals(args[0]))
                .forEach(keyed -> {
                    if (keyed instanceof ShapedRecipe) {
                        ShapedRecipe recipe = (ShapedRecipe) keyed;
                        ItemStack[] matrix = new ItemStack[9];
                        if (recipe.getShape().length >= 1) a(matrix, recipe.getShape()[0].toCharArray(), recipe.getIngredientMap(), 0);
                        if (recipe.getShape().length >= 2) a(matrix, recipe.getShape()[1].toCharArray(), recipe.getIngredientMap(), 3);
                        if (recipe.getShape().length >= 3) a(matrix, recipe.getShape()[2].toCharArray(), recipe.getIngredientMap(), 6);
                        player.openInventory(new RecipeGui(matrix, recipe.getResult()).getInventory());
                    } else {
                        ShapelessRecipe recipe = (ShapelessRecipe) keyed;
                        List<ItemStack> matrix = new ArrayList<>(9);
                        matrix.addAll(recipe.getIngredientList());
                        player.openInventory(new RecipeGui(matrix.toArray(new ItemStack[9]), recipe.getResult()).getInventory());
                    }
                });
    }

    private void a(ItemStack[] matrix, char[] c, Map<Character, ItemStack> ingredientMap, int offset) {
        if (c.length >= 1) matrix[offset] = ingredientMap.get(c[0]);
        if (c.length >= 2) matrix[offset+1] = ingredientMap.get(c[1]);
        if (c.length >= 3) matrix[offset+2] = ingredientMap.get(c[2]);
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) return getRecipes();
        if (args.length == 1) return filterArgsList(getRecipes(), args[0]);
        return Collections.emptyList();
    }

    private static CollectionList<String> getRecipes() {
        CollectionList<Recipe> recipes = new CollectionList<>();
        Bukkit.recipeIterator().forEachRemaining(recipes::add);
        return recipes.filter(recipe -> recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe)
                .map(r -> (Keyed) r)
                .map(k -> k.getKey().toString());
    }

    private static CollectionList<String> filterArgsList(CollectionList<String> list, String s) {
        return list.filter(s2 -> {
            String l = s2.toLowerCase();
            String l2 = s.toLowerCase();
            return l.replaceAll(".*:(.*)", "$1").startsWith(l2.replaceAll(".*:(.*)", "$1")) || l.startsWith(l2);
        });
    }
}
