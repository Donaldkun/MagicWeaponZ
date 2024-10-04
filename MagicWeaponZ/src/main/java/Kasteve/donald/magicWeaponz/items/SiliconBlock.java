package Kasteve.donald.magicWeaponz.items;

import Kasteve.donald.magicWeaponz.MagicWeaponz;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class SiliconBlock implements Listener {
    @EventHandler
    public static void registerRecipes() {
        ItemStack VH = new ItemStack(Material.WHITE_TERRACOTTA);

        ItemMeta VHMeta = VH.getItemMeta();
        VHMeta.setDisplayName(ChatColor.ITALIC + "高純度シリコン");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "うん、シリコン。");
        lore.add(ChatColor.GREEN + "サイボーグ"+ChatColor.WHITE+"を作るために使われる。");
        VHMeta.setLore(lore);
        VHMeta.addEnchant(Enchantment.FORTUNE, 1 ,true);
        VH.setItemMeta(VHMeta);

        ShapedRecipe VHRecipe = new ShapedRecipe(new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Silicon_block"), VH);
        VHRecipe.shape("SSS", "SSS", "SSS");
        VHRecipe.setIngredient('S', new RecipeChoice.ExactChoice(getUltimate()));


        Bukkit.addRecipe(VHRecipe);
    }
    private static ItemStack getUltimate() {
        ItemStack VH = new ItemStack(Material.WHITE_DYE);
        ItemMeta VHMeta = VH.getItemMeta();
        VHMeta.setDisplayName(ChatColor.WHITE+"シリコンの欠片");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "YES IT'S SILICON!");
        lore.add(ChatColor.ITALIC + "高純度シリコン"+ ChatColor.WHITE +"を作成できる。");
        VHMeta.setLore(lore);
        VH.setItemMeta(VHMeta);
        return VH;
    }
}