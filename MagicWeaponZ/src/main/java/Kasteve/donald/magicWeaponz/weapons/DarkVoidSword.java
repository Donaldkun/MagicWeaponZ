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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        lore.add(ChatColor.WHITE + "しかし落下ダメージがあるので、");
        lore.add(ChatColor.WHITE + "サバイバルで使うことは非推奨");
        SwordMeta.setLore(lore);
        SwordMeta.addEnchant(Enchantment.SHARPNESS,7,true);
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
        meta.setDisplayName(ChatColor.DARK_PURPLE+"虚空の心");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "漆黒にそまった奈落の心。");
        lore.add(ChatColor.WHITE+"テレポート能力を手に入れるために使用される。");
        meta.setLore(lore);
      //  meta.addEnchant(Enchantment.SHARPNESS,3,true);
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
        lore.add(ChatColor.WHITE + "しかし落下ダメージがあるので、");
        lore.add(ChatColor.WHITE + "サバイバルで使うことは非推奨");

        if (item != null && item.getType() == Material.STONE_SWORD && item.getItemMeta().getLore().equals(lore)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);

                Block block =player.getTargetBlock((Set<Material>) null,  8);
                Location location = block.getLocation();
                float pitch = player.getEyeLocation().getPitch();
                float yaw = player.getEyeLocation().getYaw();
                location.add(0,  1,  0);
                location.setYaw(yaw);
                location.setPitch(pitch);
                player.teleport (location);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,  2, 1);
            }
        }
    }
}
