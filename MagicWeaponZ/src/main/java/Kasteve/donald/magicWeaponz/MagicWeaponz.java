


package Kasteve.donald.magicWeaponz;

import Kasteve.donald.magicWeaponz.items.*;
import Kasteve.donald.magicWeaponz.weapons.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public final class MagicWeaponz extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Weapons
        getServer().getPluginManager().registerEvents(new DarkVoidSword(), this);
        DarkVoidSword.registerRecipes();
        getServer().getPluginManager().registerEvents(new MidasSword(), this);
        MidasSword.registerRecipes();

        getServer().getPluginManager().registerEvents(new CyborgTX102(), this);
        CyborgTX102.registerRecipes();
        //items
        getServer().getPluginManager().registerEvents(new Heartofthevoid(), this);
        Heartofthevoid.registerRecipes();
        getServer().getPluginManager().registerEvents(new VoidFragment(), this);
        VoidFragment.registerRecipes();


    //    getServer().getPluginManager().registerEvents(new CancelPlace(), this);



        getServer().getPluginManager().registerEvents(new PureGold(), this);
        PureGold.registerRecipes();
        getServer().getPluginManager().registerEvents(new MidassAlloy(), this);
        MidassAlloy.registerRecipes();

        getServer().getPluginManager().registerEvents(new NecroticHandle(), this);
        NecroticHandle.registerRecipes();

        getServer().getPluginManager().registerEvents(new SiliconFlagment(), this);
        SiliconFlagment.registerRecipes();
        getServer().getPluginManager().registerEvents(new SiliconBlock(), this);
        SiliconBlock.registerRecipes();
        getServer().getPluginManager().registerEvents(new Eyeball(), this);
        Eyeball.registerRecipes();

        getServer().getPluginManager().registerEvents(new PlasmaShield(), this);
        PlasmaShield.registerRecipes();

        getServer().getPluginManager().registerEvents(new Jester(), this);
        Jester.registerRecipes();

        getServer().getPluginManager().registerEvents(new HealingWand(), this);
        HealingWand.registerRecipes();


        //Entitys
        getServer().getPluginManager().registerEvents(new NecroticFragment(), this);

        getServer().getPluginManager().registerEvents(new Plasma(), this);


    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("MagicWeaponZが無効化されました。");
    }
}
