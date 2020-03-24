package kr.kro.jhseo1107.discord;

import kr.kro.jhseo1107.Main;
import kr.kro.jhseo1107.translation.TranslationString;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CommandEvent extends ListenerAdapter {
    JDA jda;
    private JavaPlugin main;
    public CommandEvent(JavaPlugin plugin)
    {
        main = plugin;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        Role role = event.getGuild().getRoleById(Main.roleid);
        Member member = event.getGuild().getMember(event.getAuthor());
        List<Role> member_roles = member.getRoles();
        if (content.contains("!" + Main.dccommand)) {
            MessageChannel channel = event.getChannel();
            String[] args = content.split(" ");
            if (channel.getId().equals(Main.channelid)) {
                if(args.length<2)
                {
                    channel.sendMessage(TranslationString.registerfail[Main.langint]+TranslationString.notenougharguments[Main.langint]).queue();
                    channel.sendMessage(TranslationString.trytext[Main.langint]+Main.dccommand+TranslationString.minecraftnickname[Main.langint]).queue();
                    return;
                }
                else
                {
                    if(member_roles.contains(role))
                    {
                        channel.sendMessage(TranslationString.registerfail[Main.langint]+TranslationString.wrongrole[Main.langint]).queue();
                        return;
                    }
                    else
                    {
                        if(args[1].matches("[a-zA-Z0-9]*"))
                        {
                            channel.sendMessage(TranslationString.registerstart[Main.langint] + args[1]).queue();
                            String commandtext = Main.mccommand;
                            commandtext = commandtext.replace("NICKNAME",args[1]);
                            final String commandfinal = commandtext;
                            try {
                                boolean success = Bukkit.getScheduler().callSyncMethod( main, new Callable<Boolean>() {
                                    @Override
                                    public Boolean call() {
                                        return Bukkit.dispatchCommand( Bukkit.getConsoleSender(),commandfinal );
                                    }
                                } ).get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            event.getGuild().addRoleToMember(member, role).queue();
                            channel.sendMessage(TranslationString.registerdone[Main.langint]+Main.serverip).queue();
                            return;
                        }
                        else
                        {
                            channel.sendMessage(TranslationString.registerfail[Main.langint]+TranslationString.specialletters[Main.langint]).queue();
                        }
                    }
                }
            } else {
                channel.sendMessage(TranslationString.registerfail[Main.langint]+TranslationString.wrongchannel[Main.langint]).queue();
                return;
            }
        }
    }
}