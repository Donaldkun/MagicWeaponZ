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

public class Heartofthevoid implements Listener {
    @EventHandler
    public static void registerRecipes() {
        ItemStack VH = new ItemStack(Material.FLINT);

        ItemMeta VHMeta = VH.getItemMeta();
        VHMeta.setDisplayName(ChatColor.DARK_PURPLE+"虚空の心");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "漆黒にそまった奈落の心。");
        lore.add(ChatColor.WHITE+"テレポート能力を手に入れるために使用される。");
        VHMeta.setLore(lore);
        VHMeta.addEnchant(Enchantment.FORTUNE, 1 ,true);
        VH.setItemMeta(VHMeta);

        ShapedRecipe VHRecipe = new ShapedRecipe(new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Heart_of_the_Void"), VH);
        VHRecipe.shape("AAA", "AAA", "AAA");
        VHRecipe.setIngredient('A', new RecipeChoice.ExactChoice(getUltimate()));


        Bukkit.addRecipe(VHRecipe);
    }
    private static ItemStack getUltimate() {
        ItemStack I = new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON);
        ItemMeta meta = I.getItemMeta();
        meta.addEnchant(Enchantment.FORTUNE, 1 ,false);
        meta.setDisplayName(ChatColor.DARK_PURPLE+"虚空の欠片");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "漆黒にそまった奈落の欠片");
        lore.add(ChatColor.WHITE+"虚空の心を作成するために使用される");
        meta.setLore(lore);
        I.setItemMeta(meta);
        return I;
    }
}
