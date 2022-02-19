package com.github.entenspezialsauce.commands;

import com.github.entenspezialsauce.EntenListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class HelpCommand extends Command{

    public HelpCommand() {
        super("help", "h", "imstuck");
    }

    @Override
    public void invoke(MessageReceivedEvent event, String... arguments) {
        EmbedBuilder helpBuilder = new EmbedBuilder();
        MessageChannel channel = event.getChannel();
        helpBuilder.setTitle("Oh no... you don't know how this works? Let me show you.", null);
        helpBuilder.setColor(new Color(154, 61, 21));
        var entenListener = new EntenListener();
        var commandList = entenListener.getCommands();
        int counter = 1;
        StringBuilder helpString = new StringBuilder();
        for (Command commands:commandList) {
            helpString.append(counter++).append(": ").append(entenListener.getPrefix()).append(commands.getInvoker()).append("\n");
            helpString.append("Hier könnte ihre Werbung stehen").append("\n");
        }
        helpBuilder.setDescription(helpString.toString());
        channel.sendMessageEmbeds(helpBuilder.build()).queue();
    }
}
