package Kasteve.donald.magicWeaponz.RuinedItems;

import Kasteve.donald.magicWeaponz.MagicWeaponz;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class OverClockSword implements Listener {

    public static void registerRecipes(){
        ItemStack Sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta SwordMeta = Sword.getItemMeta();
        SwordMeta.setDisplayName(ChatColor.BLUE+"Over-clock Sword");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "アビリティ：" + ChatColor.GREEN + ChatColor.ITALIC + "OC-Mode"+ChatColor.YELLOW+"手に持っている間に発動。");
        lore.add(ChatColor.WHITE + "チャージをひとつ消費して発動する。");
        lore.add(ChatColor.WHITE + "自分の"+ChatColor.RED+"♥最大体力"+ChatColor.WHITE+"の6倍以上のダメージを無効化する。");
        lore.add(ChatColor.WHITE + "ただし、1秒だけ動けなくなる。");
        lore.add(ChatColor.WHITE + "チャージ : 300");
        lore.add(ChatColor.WHITE + "サバイバルでこれ作れたらすごすぎる");
        lore.add(ChatColor.GRAY + "☣Mechanic☣");

        SwordMeta.setLore(lore);
        SwordMeta.addEnchant(Enchantment.SHARPNESS,25,true);
        SwordMeta.setUnbreakable(true);
        Sword.setItemMeta(SwordMeta);


        NamespacedKey key = new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Over_Clock_Sword");
        ShapedRecipe recipe = new ShapedRecipe(key, Sword);
        recipe.shape("AHA", "GHG", "AHA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(getAlloy()));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(Handle()));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(getGold()));



        Bukkit.addRecipe(recipe);
    }
    private static ItemStack Handle() {
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

        return VH;
    }
    private static ItemStack getAlloy() {
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

        return VH;
    }
    private static ItemStack getGold() {
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
