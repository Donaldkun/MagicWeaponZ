package Kasteve.donald.magicWeaponz.weapons;

import Kasteve.donald.magicWeaponz.MagicWeaponz;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class MidasSword implements Listener {
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public static void registerRecipes() {

        ItemStack Sword = new ItemStack(Material.GOLDEN_SWORD);
        // アイテムに名前を設定
        ItemMeta SwordMeta = Sword.getItemMeta();
        SwordMeta.setDisplayName(ChatColor.GOLD+"Midas's Katana");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "アビリティ：" + ChatColor.GOLD + ChatColor.ITALIC + "Greed"+ChatColor.YELLOW + " 右クリック");
        lore.add(ChatColor.WHITE + "10秒間プレイヤーのステータス強化！");
        lore.add(ChatColor.WHITE + "スピード III、攻撃力 III、跳躍力 III を付与");
        lore.add(ChatColor.AQUA + "＊クールダウン"+ChatColor.WHITE +":" + ChatColor.RESET + "" + ChatColor.BLUE + "20秒");
        lore.add(ChatColor.DARK_RED + "♥体力コスト"+ChatColor.WHITE+":4");
        SwordMeta.setLore(lore);
        SwordMeta.addEnchant(Enchantment.LOOTING,5,true);
        SwordMeta.addEnchant(Enchantment.SHARPNESS,12,true);
        SwordMeta.addEnchant(Enchantment.SWEEPING_EDGE,6,true);
        SwordMeta.addEnchant(Enchantment.THORNS,1,true);

        SwordMeta.setUnbreakable(true);
        Sword.setItemMeta(SwordMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Midass_Katana"), Sword);
        recipe.shape("  A", "GA ", "HG ");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(getAlloy()));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(getGold()));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(Handle()));

        Bukkit.addRecipe(recipe);
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

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        long currentTime = System.currentTimeMillis();

        UUID playerUUID = player.getUniqueId();



        if (item != null && item.getType() == Material.GOLDEN_SWORD && item.getItemMeta().getLore().contains(ChatColor.WHITE + "10秒間プレイヤーのステータス強化！"
        )) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (cooldowns.containsKey(playerUUID) && cooldowns.get(playerUUID) > currentTime) {
                    long remainingTime = (cooldowns.get(playerUUID) - currentTime - 1000) / 1000; // 秒に変換
                    player.sendMessage(ChatColor.AQUA + "アビリティークールダウン:" + remainingTime + "秒");
                    return;
                }

                cooldowns.put(playerUUID, currentTime + 20000);
                event.setCancelled(true);
                player.damage(4);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 200, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200, 2));
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK,  2, 1);

                   double x = player.getLocation().getX();
                   double y = player.getLocation().getY();
                   double z = player.getLocation().getZ();

                player.getWorld().spawnParticle(Particle.LAVA, x, y, z, 1, 3, 3, 3, 3);


            }
        }
    }
}

