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

public class MidassAlloy implements Listener {
    @EventHandler
    public static void registerRecipes() {
        ItemStack VH = new ItemStack(Material.COPPER_BLOCK);

        ItemMeta VHMeta = VH.getItemMeta();
        VHMeta.setDisplayName(ChatColor.GOLD+"Midas合金");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "ものすごく固い合金");
        lore.add(ChatColor.WHITE + "強欲系のツールの作成に使われる。");
        lore.add(ChatColor.WHITE + "Midasによって発見されました。");
        VHMeta.setLore(lore);
        VHMeta.addEnchant(Enchantment.FORTUNE, 1 ,true);
        VH.setItemMeta(VHMeta);

        ShapedRecipe VHRecipe = new ShapedRecipe(new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Midas_Alloy"), VH);
        VHRecipe.shape("ACA", "CBC", "ACA");
        VHRecipe.setIngredient('B', Material.NETHERITE_INGOT);
        VHRecipe.setIngredient('C', Material.IRON_BLOCK);
        VHRecipe.setIngredient('A', new RecipeChoice.ExactChoice(getUltimate()));


        Bukkit.addRecipe(VHRecipe);
    }
    private static ItemStack getUltimate() {
        ItemStack VH = new ItemStack(Material.GOLD_INGOT);

        ItemMeta VHMeta = VH.getItemMeta();
        VHMeta.setDisplayName(ChatColor.GOLD+"精製された金");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "純度99.99%の金！");
        lore.add(ChatColor.WHITE+"強欲の象徴である。");
        VHMeta.setLore(lore);
        VHMeta.addEnchant(Enchantment.FORTUNE, 1 ,true);
        VH.setItemMeta(VHMeta);
        return VH;
    }

}
