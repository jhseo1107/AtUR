package kr.kro.jhseo1107;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import kr.kro.jhseo1107.commands.Atur;
import kr.kro.jhseo1107.discord.BotClass;

public class Main extends JavaPlugin{

    public static String mccommand;
    public static String bottoken;
    public static String channelid;
    public static String roleid;
    public static String dccommand;
    public static String serverip;
    public static String lang;
    public static int langint;

    @Override
    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "AtUR by jhseo1107 - active");
        this.getCommand("atur").setExecutor(new Atur());
        saveDefaultConfig();

        mccommand = getConfig().getString("minecraft.command");
        bottoken = getConfig().getString("discord.tokens.bot_token");
        channelid = getConfig().getString("discord.tokens.channel_id");
        roleid = getConfig().getString("discord.tokens.role_id");
        dccommand = getConfig().getString("discord.command");
        serverip = getConfig().getString("minecraft.server-ip");
        lang = getConfig().getString("language");

        if(lang.equals("en_us"))
            langint = 0;
        else if(lang.equals("ko_kr"))
            langint = 1;
        else
            langint = 0;

        Bukkit.getConsoleSender().sendMessage(mccommand+" "+bottoken+" "+channelid+" "+roleid+" "+dccommand);

        new BotClass(this);
    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "AtUR by jhseo1107 - unactive");

    }
}
