package Kasteve.donald.magicWeaponz.weapons;

import Kasteve.donald.magicWeaponz.MagicWeaponz;
import Kasteve.donald.magicWeaponz.tools.CustomHead;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.nio.DoubleBuffer;
import java.util.*;

public class PlasmaShield implements Listener {

    public boolean sh=false;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public static void registerRecipes() {

        ItemStack Sword = new ItemStack(Material.SHIELD);

        ItemMeta SwordMeta = Sword.getItemMeta();
        SwordMeta.setDisplayName(ChatColor.AQUA+"Plasma"+ChatColor.LIGHT_PURPLE+" Shield");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "アビリティ：" + ChatColor.GOLD + ChatColor.ITALIC + "PlasmaShield"+ChatColor.YELLOW + " SHIFTと右クリック");
        lore.add(ChatColor.WHITE+"5秒間ダメージを完全無効化");
        lore.add(ChatColor.WHITE+"ただし、自分の体力を超える攻撃は無効化できない");
        lore.add(ChatColor.WHITE+"オフハンドで使用してください");
        lore.add(ChatColor.AQUA + "＊クールダウン"+ChatColor.WHITE +":" + ChatColor.RESET + "" + ChatColor.BLUE + "20秒");
        SwordMeta.setLore(lore);
        SwordMeta.addEnchant(Enchantment.THORNS,3,true);
        SwordMeta.setUnbreakable(true);
        Sword.setItemMeta(SwordMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(JavaPlugin.getPlugin(MagicWeaponz.class), "Plasma_Shield"), Sword);
        recipe.shape("GGG", "GPG", " G ");
        recipe.setIngredient('P', new RecipeChoice.ExactChoice(getPlasma()));
        recipe.setIngredient('G', new RecipeChoice.ExactChoice(getGold()));

        Bukkit.addRecipe(recipe);
    }

    private static ItemStack getPlasma() {
        String texture ="e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFjNjI4YmQ3YTIzZWM2ZDdjZjg4NzkzYTdlNDFlMDY2MTk3MTU5OWNlZjViODNmZmE2NzhhNmY0NWQyMDhkMCJ9fX0=";

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "プラズマ");
        lore.add(ChatColor.WHITE + "個体、液体、気体、そして"+ChatColor.AQUA+"プラズマ");
        lore.add(ChatColor.WHITE + "あなたの腕は頑張れば黒曜石ぐらい壊せるから、");
        lore.add(ChatColor.WHITE + "持っても平気！兵器！");
        ItemStack customHead = CustomHead.getHead(texture, UUID.fromString("f23b62ed-2e54-4cbe-8f3f-02e045fa135e"),ChatColor.AQUA+"Plasma",lore);

        return customHead;
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


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.equals(Material.SHIELD)){
            item = player.getInventory().getItemInOffHand();
        }
        long currentTime = System.currentTimeMillis();


        UUID playerUUID = player.getUniqueId();




        if (item != null && item.getType() == Material.SHIELD && item.getItemMeta().getLore().contains(ChatColor.WHITE+"5秒間ダメージを完全無効化")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (player.isSneaking()) {

                    if (cooldowns.containsKey(playerUUID) && cooldowns.get(playerUUID) > currentTime) {
                        long remainingTime = (cooldowns.get(playerUUID) - currentTime - 1000) / 1000; // 秒に変換// ;
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "シールド"+ChatColor.AQUA+"クールダウン:" + remainingTime + "秒");
                        return;
                    }

                    cooldowns.put(playerUUID, currentTime + 20000);
                    event.setCancelled(true);

                    player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0.5f);

                    sh = true;

                    new BukkitRunnable() {
                        private final int numParticles = 15; // 数
                        private final double radius = 4.0;   // 半径
                        private final double height = 0.0;   // 高さ
                        private final double increment = (2 * Math.PI) / numParticles;
                        private int ticksPassed = 0;


                        @Override
                        public void run() {
                            ticksPassed=ticksPassed+2;
                            if (ticksPassed > 100 || player.isDead()) {
                                this.cancel();
                                sh = false;
                            }

                            for (int i = 0; i < numParticles; i++) {
                                double playerx = player.getLocation().getX();
                                double playery = player.getLocation().getY();
                                double playerz = player.getLocation().getZ();
                                double angle = i * increment;
                                double x = playerx + radius * Math.cos(angle);
                                double z = playerz + radius * Math.sin(angle);
                                double y = playery + height;

                                player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, x, y, z, 0, 0, 0, 0, 1);
                                player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, x, y + 0.5, z, 0, 0, 0, 0, 1);
//                            player.getWorld().spawnParticle(Particle.END_ROD, x, y+1, z, 0, 0, 0, 0, 1);
                                player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, x, y - 0.5, z, 0, 0, 0, 0, 1);
//                            player.getWorld().spawnParticle(Particle.END_ROD, x, y+1.5, z, 0, 0, 0, 0, 1);

                            }
                        }
                    }.runTaskTimer(JavaPlugin.getPlugin(MagicWeaponz.class), 0L, 2L);


                }
            }
        }
    }
    @EventHandler
    public void NightVision(EntityDamageEvent event){
        if (event.getEntityType().equals(EntityType.PLAYER)&& sh) {
            Player player = (Player) event.getEntity();
            if (event.getDamage()<player.getHealth()) {
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_HURT, 1, 1);
            }
        }
    }
    @EventHandler
    public void  Bulse(PlayerJoinEvent e){
        sh=false;
    }
}