package Kasteve.donald.magicWeaponz.weapons;

import Kasteve.donald.magicWeaponz.MagicWeaponz;
import Kasteve.donald.magicWeaponz.tools.CustomHead;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CyborgTX102 implements Listener {

    public static void registerRecipes(){
        String base64Texture = "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmJhYmRkN2Y3MjRmYTViNWZkMTE4MzE4YWZhNTdjNmZmN2Q0NGVmMTdjMDhjNTRhNmQ2MmFiMTNmYjgxMjk4MCJ9fX0="; // 正しいBase64テクスチャデータ

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "アビリティ：" + ChatColor.GREEN + ChatColor.ITALIC + "Cyborg Eye");
        lore.add(ChatColor.WHITE + "着用している間に暗視の効果をあたえます。");
        lore.add(ChatColor.RED + "♥体力"+ChatColor.WHITE+"も増える優れもの。");
        lore.add(ChatColor.RED + "♥体力:+2");
        lore.add(ChatColor.GREEN + "❃CYBORG❃");

        ItemStack customHead = CustomHead.getHead(base64Texture, UUID.fromString("68981142-6352-481c-96e9-b5de42df11d5"),ChatColor.RESET+"Cyborg"+ChatColor.RED+"TX-102",lore);
        //metas
        ItemMeta meta = customHead.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION,7,true);
        meta.addEnchant(Enchantment.THORNS,4,true);
        customHead.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Cyborg_TX102");
        ShapedRecipe recipe = new ShapedRecipe(key, customHead);
        recipe.shape("SII", "SIE", "SSI");
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('E', new RecipeChoice.ExactChoice(getEyeball()));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(getSilicon()));



        Bukkit.addRecipe(recipe);
    }
    private static ItemStack getSilicon() {
        ItemStack VH = new ItemStack(Material.WHITE_TERRACOTTA);

        ItemMeta VHMeta = VH.getItemMeta();
        VHMeta.setDisplayName(ChatColor.ITALIC + "高純度シリコン");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "うん、シリコン。");
        lore.add(ChatColor.GREEN + "サイボーグ"+ChatColor.WHITE+"を作るために使われる。");
        VHMeta.setLore(lore);
        VHMeta.addEnchant(Enchantment.FORTUNE, 1 ,true);
        VH.setItemMeta(VHMeta);

        return VH;
    }
    private static ItemStack getEyeball() {
        String base64Texture = "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzIxYTYyZjhjY2I1Zjg3NDYyYzgxOWE0NzI4OGYwZTc2ZWM1MzZlMTQwODZkMWNiYWQxNTU4NDdkYWRjOWIwMCJ9fX0="; // 正しいBase64テクスチャデータ
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "サイボーグの目");
        lore.add(ChatColor.WHITE + "そのままでは使えない");
        lore.add(ChatColor.GREEN + "❃CYBORG❃");
        ItemStack customHead = CustomHead.getHead(base64Texture, UUID.fromString("c4b1c406-9417-454a-82cb-2216aa21fab6"),ChatColor.RED+"人工眼球",lore);
        return customHead;
    }
    @EventHandler
    public void NightVision(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        ItemStack newHelmet = event.getNewItem();
        ItemStack oldHelmet = event.getOldItem();

        // 装備したとき
        if (newHelmet.getType() == Material.PLAYER_HEAD && newHelmet.getItemMeta().getLore().contains(ChatColor.YELLOW + "アビリティ：" + ChatColor.GREEN + ChatColor.ITALIC + "Cyborg Eye")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false, false));

          //  double currentMaxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(24.0);
        } else if (oldHelmet.getType() == Material.PLAYER_HEAD && oldHelmet.getItemMeta().getLore().contains(ChatColor.YELLOW + "アビリティ：" + ChatColor.GREEN + ChatColor.ITALIC + "Cyborg Eye")) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);

         //   double currentMaxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);

        }
    }
    @EventHandler
    public void deadefect(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        ItemStack Helmet = event.getPlayer().getInventory().getHelmet();
        if (Helmet != null &&Helmet.getType() == Material.PLAYER_HEAD && Helmet.getItemMeta().getLore().contains(ChatColor.YELLOW + "アビリティ：" + ChatColor.GREEN + ChatColor.ITALIC + "Cyborg Eye")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false, false));
        }else {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
        }
    }
}