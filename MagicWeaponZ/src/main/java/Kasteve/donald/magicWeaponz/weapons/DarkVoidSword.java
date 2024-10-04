package Kasteve.donald.magicWeaponz.weapons;

import Kasteve.donald.magicWeaponz.MagicWeaponz;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class DarkVoidSword implements Listener {

    public static void registerRecipes() {

        ItemStack Sword = new ItemStack(Material.STONE_SWORD);
        // アイテムに名前を設定
        ItemMeta SwordMeta = Sword.getItemMeta();
        SwordMeta.setDisplayName(ChatColor.ITALIC+"Dark Void Sword");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "アビリティ：" + ChatColor.RESET + "" + ChatColor.ITALIC + "Teleport"+ChatColor.YELLOW + " 右クリック");
        lore.add(ChatColor.WHITE + "前方向にテレポートできる");
        lore.add(ChatColor.WHITE + "最大8ブロックテレポート可能！");
        SwordMeta.setLore(lore);
        SwordMeta.addEnchant(Enchantment.SHARPNESS,6,true);
        SwordMeta.setUnbreakable(true);
        Sword.setItemMeta(SwordMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Dark_Void_Sword"), Sword);
        recipe.shape(" E ", "EEE", " A ");
        recipe.setIngredient('A', Material.STICK);
        recipe.setIngredient('E', new RecipeChoice.ExactChoice(getVW()));

        Bukkit.addRecipe(recipe);
    }

    private static ItemStack getVW() {
        ItemStack MC = new ItemStack(Material.FLINT);
        ItemMeta meta = MC.getItemMeta();
        meta.addEnchant(Enchantment.FORTUNE, 1 ,false);
        meta.setDisplayName(ChatColor.DARK_PURPLE+"虚空の心");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "漆黒にそまった奈落の心。");
        lore.add(ChatColor.WHITE+"テレポート能力を手に入れるために使用される。");
        meta.setLore(lore);
        MC.setItemMeta(meta);

        return MC;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "アビリティ：" + ChatColor.RESET + "" + ChatColor.ITALIC + "Teleport"+ChatColor.YELLOW + " 右クリック");
        lore.add(ChatColor.WHITE + "前方向にテレポートできる");
        lore.add(ChatColor.WHITE + "最大8ブロックテレポート可能！");

        if (item != null && item.getType() == Material.STONE_SWORD && item.getItemMeta().getLore().equals(lore)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);
             player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 1, 1));
                Block block =player.getTargetBlock(null,  8);
                Location location = block.getLocation();
                float pitch = player.getEyeLocation().getPitch();
                float yaw = player.getEyeLocation().getYaw();
                location.add(0,  1,  0);
                location.setYaw(yaw);
                location.setPitch(pitch);
                player.teleport (location);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,  2, 1);
                player.setVelocity(new Vector(0, 0, 0));
            }
        }
    }
}
