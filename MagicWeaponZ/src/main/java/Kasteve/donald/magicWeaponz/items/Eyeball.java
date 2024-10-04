package Kasteve.donald.magicWeaponz.items;

import Kasteve.donald.magicWeaponz.MagicWeaponz;
import Kasteve.donald.magicWeaponz.tools.CustomHead;
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
import java.util.UUID;

public class Eyeball implements Listener {
    @EventHandler
    public static void registerRecipes(){
        String base64Texture = "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzIxYTYyZjhjY2I1Zjg3NDYyYzgxOWE0NzI4OGYwZTc2ZWM1MzZlMTQwODZkMWNiYWQxNTU4NDdkYWRjOWIwMCJ9fX0="; // 正しいBase64テクスチャデータ
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "サイボーグの目");
        lore.add(ChatColor.WHITE + "そのままでは使えない");
        lore.add(ChatColor.GREEN + "❃CYBORG❃");
        ItemStack customHead = CustomHead.getHead(base64Texture, UUID.fromString("c4b1c406-9417-454a-82cb-2216aa21fab6"),ChatColor.RED+"人工眼球",lore);
        // カスタムクラフトレシピを追加
        NamespacedKey key = new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Synthetic_Eye");
        ShapedRecipe recipe = new ShapedRecipe(key, customHead);
        recipe.shape("RRA", "RIA", "RRA");
        recipe.setIngredient('I', Material.GLASS);
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        recipe.setIngredient('A', Material.AMETHYST_SHARD);

        Bukkit.addRecipe(recipe);
    }}