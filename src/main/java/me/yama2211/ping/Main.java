package me.yama2211.ping;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "コンソールから実行できません。");
            return true;
        } else {
        if(args.length == 0){
            Player player = (Player)sender;
            sender.sendMessage(ChatColor.GOLD+"Ping: "+ ChatColor.BOLD + getPing(player) + ChatColor.RESET + ChatColor.GOLD + " ms");
            return true;
        }
    }
        return true;
    }

    private int getPing(Player p) {
        String ver = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        int ping = -1;
        try {
            Class<?> cp = Class.forName("org.bukkit.craftbukkit."+ ver +".entity.CraftPlayer");
            Object cpc = cp.cast(p);
            Method m = cpc.getClass().getMethod("getHandle");
            Object o = m.invoke(cpc);
            Field f = o.getClass().getField("ping");
            ping = f.getInt(o);
        } catch (Exception e) {
            Bukkit.getLogger().warning(e.getLocalizedMessage());
        }
        return ping;
    }
}
