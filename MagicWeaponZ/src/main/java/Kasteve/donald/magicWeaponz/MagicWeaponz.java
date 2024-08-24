


package Kasteve.donald.magicWeaponz;

import Kasteve.donald.magicWeaponz.items.CancelPlace;
import Kasteve.donald.magicWeaponz.items.Heartofthevoid;
import Kasteve.donald.magicWeaponz.items.VoidFragment;
import Kasteve.donald.magicWeaponz.weapons.DarkVoidSword;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicWeaponz extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Weapons
        getServer().getPluginManager().registerEvents(new DarkVoidSword(), this);
        DarkVoidSword.registerRecipes();
        //items
        getServer().getPluginManager().registerEvents(new Heartofthevoid(), this);
        Heartofthevoid.registerRecipes();
        getServer().getPluginManager().registerEvents(new VoidFragment(), this);
        VoidFragment.registerRecipes();
        getServer().getPluginManager().registerEvents(new CancelPlace(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("MagicWeaponZが無効化されました。");
    }
}
