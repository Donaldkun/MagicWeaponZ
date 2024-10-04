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
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class VoidFragment implements Listener {


    @EventHandler
    public static void registerRecipes() {
    ItemStack VH = new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON);

    ItemMeta VHMeta = VH.getItemMeta();
    VHMeta.addEnchant(Enchantment.FORTUNE, 1 ,false);
    VHMeta.setDisplayName(ChatColor.DARK_PURPLE+"虚空の欠片");
    List<String> lore = new ArrayList<>();
    lore.add(ChatColor.WHITE + "漆黒にそまった奈落の欠片");
    lore.add(ChatColor.WHITE+"虚空の心を作成するために使用される");
    VHMeta.setLore(lore);
    VH.setItemMeta(VHMeta);


        ShapedRecipe VHRecipe = new ShapedRecipe(new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Void_Fragment"), VH);
    VHRecipe.shape("XXX", "XXX", "XXX");
    VHRecipe.setIngredient('X', Material.ENDER_PEARL);

    Bukkit.addRecipe(VHRecipe);
}

}
