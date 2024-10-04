package Kasteve.donald.magicWeaponz.weapons;

import Kasteve.donald.magicWeaponz.MagicWeaponz;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
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

public class HealingWand implements Listener {
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public static void registerRecipes() {

        ItemStack Sword = new ItemStack(Material.BLAZE_ROD);
        // アイテムに名前を設定
        ItemMeta SwordMeta = Sword.getItemMeta();
        SwordMeta.setDisplayName(ChatColor.GOLD+"Healers Wand");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "アビリティ：" + ChatColor.GOLD + ChatColor.ITALIC + "Greedy Healer"+ChatColor.YELLOW + " 右クリック");
        lore.add(ChatColor.RED + "♥体力"+ChatColor.WHITE+"を1秒に1つ、5秒間回復する");
        lore.add(ChatColor.AQUA + "＊クールダウン"+ChatColor.WHITE +":" + ChatColor.RESET + "" + ChatColor.BLUE + "10秒");
        SwordMeta.setLore(lore);
        SwordMeta.addEnchant(Enchantment.THORNS,20,true);

        SwordMeta.setUnbreakable(true);
        Sword.setItemMeta(SwordMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Healers_Wand"), Sword);
        recipe.shape("GPG", " A ", " H ");
        recipe.setIngredient('P',Material.ENCHANTED_GOLDEN_APPLE);
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

        // クールダウン


        if (item != null && item.getType() == Material.BLAZE_ROD && item.getItemMeta().getLore().contains(ChatColor.RED + "♥体力"+ChatColor.WHITE+"を1秒に1つ、5秒間回復する")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (cooldowns.containsKey(playerUUID) && cooldowns.get(playerUUID) > currentTime) {
                    long remainingTime = (cooldowns.get(playerUUID) - currentTime - 1000) / 1000; // 秒に変換// ;
                    player.sendMessage(ChatColor.AQUA + "アビリティークールダウン:" + remainingTime + "秒");
                    return;
                }

                cooldowns.put(playerUUID, currentTime + 10000);
                event.setCancelled(true);

                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK,  1, 2);

                new BukkitRunnable() {
                    int counter = 0;
                    @Override
                    public void run() {
                        if (counter >= 5 || player.isDead()) {
                            cancel();
                            return;
                        }

                        double health = player.getHealth();
                        if (health < player.getMaxHealth()) {
                            player.setHealth(Math.min(player.getMaxHealth(), health + 2.0)); // 体力を回復
                        }

                        counter++;
                    }
                }.runTaskTimer(JavaPlugin.getPlugin(MagicWeaponz.class), 0, 20);


            }
        }
    }
}

