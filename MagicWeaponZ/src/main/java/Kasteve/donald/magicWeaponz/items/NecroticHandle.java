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

public class NecroticHandle implements Listener {
    @EventHandler
    public static void registerRecipes() {
        ItemStack VH = new ItemStack(Material.BONE);

        ItemMeta VHMeta = VH.getItemMeta();
        VHMeta.setDisplayName(ChatColor.DARK_PURPLE+"ネクロティックハンドル");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "光沢のある固い棒");
        lore.add(ChatColor.WHITE + "すごく強いツールの作成に使われる。");
        lore.add(ChatColor.DARK_PURPLE+"");
        lore.add(ChatColor.DARK_PURPLE+"❂NECROTIC❂");
        VHMeta.setLore(lore);
        VHMeta.addEnchant(Enchantment.BINDING_CURSE, 4 ,true);
        VH.setItemMeta(VHMeta);

        ShapedRecipe VHRecipe = new ShapedRecipe(new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Necrotic_Handle"), VH);
        VHRecipe.shape("  A", " A ", "A  ");

        VHRecipe.setIngredient('A', new RecipeChoice.ExactChoice(getUltimate()));


        Bukkit.addRecipe(VHRecipe);
    }
    private static ItemStack getUltimate() {
        ItemStack VH = new ItemStack(Material.DRIED_KELP);

        ItemMeta VHMeta = VH.getItemMeta();
        VHMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "ネクロティックフラグメント");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "呪われた何かの欠片");
        lore.add(ChatColor.DARK_PURPLE+"ネクロティックハンドル"+ChatColor.WHITE+"を作成できる。");
        lore.add(ChatColor.DARK_PURPLE+"");
        lore.add(ChatColor.DARK_PURPLE+"❂NECROTIC❂");
        VHMeta.setLore(lore);
        VHMeta.addEnchant(Enchantment.BINDING_CURSE, 4 ,true);
        VH.setItemMeta(VHMeta);
        return VH;
    }
}
