package com.github.entenspezialsauce.commands;

import com.github.entenspezialsauce.EntenListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

@Command.Info(value = "help", description = "Get some help!", aliases = {"h", "halp", "?"})
public class HelpCommand extends Command {

    @Override
    public void invoke(MessageReceivedEvent event, String... arguments){
        EmbedBuilder helpBuilder = new EmbedBuilder();
        MessageChannel channel = event.getChannel();
        helpBuilder.setTitle("Oh no... you don't know how this works? Let me show you.", null);
        helpBuilder.setColor(new Color(154, 61, 21));
        var entenListener = new EntenListener();
        var commandList = entenListener.getCommands();
        for (Command commands : commandList) {
            helpBuilder.addField(commands.getInvoker(), commands.getDescription(), false);
        }
        channel.sendMessageEmbeds(helpBuilder.build()).queue();
    }
}
