package kr.kro.jhseo1107.discord;

import javax.security.auth.login.LoginException;

import kr.kro.jhseo1107.Main;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BotClass extends ListenerAdapter{
    public JDA jda;
    public static JDABuilder builder;
    public BotClass(JavaPlugin plugin)
    {
        startBot(plugin);
    }
    private void startBot(JavaPlugin plugin) {
        try {
            builder = new JDABuilder(AccountType.BOT);
            builder.setToken(Main.bottoken);
            builder.addEventListeners(new CommandEvent(plugin));
            builder.build();

        } catch (LoginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void stop() {

    }
}
