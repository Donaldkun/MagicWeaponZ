package Kasteve.donald.magicWeaponz.RuinedItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CancelPlace implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getItemMeta().getLore() != null) {
            if (item.getType() == Material.POLISHED_BLACKSTONE_BUTTON && item.getItemMeta().getLore().contains(ChatColor.WHITE + "ものすごく固い合金")
                    || item.getType() == Material.COPPER_BLOCK && item.getItemMeta().getLore().contains(ChatColor.WHITE + "漆黒にそまった奈落の欠片")
                    || item.getType() == Material.WHITE_TERRACOTTA && item.getItemMeta().getLore().contains(ChatColor.WHITE + "サイボーグを作るために使われる。")
                    || item.getType() == Material.PLAYER_HEAD && item.getItemMeta().getLore().contains(ChatColor.GREEN + "❃CYBORG❃")
                    || item.getType() == Material.PLAYER_HEAD && item.getItemMeta().getLore().contains(ChatColor.AQUA + "プラズマ")) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
