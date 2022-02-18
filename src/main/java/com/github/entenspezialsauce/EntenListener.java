package com.github.entenspezialsauce;

import com.github.entenspezialsauce.commands.Command;
import com.github.entenspezialsauce.commands.Pong;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntenListener extends ListenerAdapter {

    private final String prefix = "!";
    private final List<Command> commands = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // We don't want to respond to other bot accounts, including ourself
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String content = message.getContentRaw();
        String commandString = content.trim();

        //Der Bot weiß, dass er angesprochen wird
        if (!isPrefixCorrect(commandString)) return;

        var attribute = getAttribute(commandString);
        var command = attribute[0];
        if (!isCommand(command)) return;

        var parameters = Arrays.copyOfRange(attribute, 1, attribute.length);

        if (command.equals("ping")) {
            new Pong().invoke(event, attribute);
        }

        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping")) {
            //channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
    }

    private String[] getAttribute(String message) {
        String command = message.replaceFirst(this.prefix, "");
        return message.split(" ");
    }

    private boolean isPrefixCorrect(String message) {
        return message.startsWith(this.prefix);
    }

    private boolean isCommand(String duckblood) {
        //cum is in cumlist
        for (Command command: commands) {
            if(command.isMe(duckblood)) return true;
        }
        return false;
    }

}
