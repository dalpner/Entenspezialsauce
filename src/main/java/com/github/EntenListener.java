package com.github;

import commands.Pong;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class EntenListener extends ListenerAdapter {

    private final String prefix = "!";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String content = message.getContentRaw();
        String commandString = content.trim();
        if (isPrefixCorrect(commandString)) {
            var command = getCommand(commandString);
            var attribute = getAttribute(commandString);
            if (command.equals("ping")){
                new Pong().invoke(event, attribute);
            }
        }
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping")) {
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
    }

    private String[] getAttribute(String message) {
        var attributes = message.split(" ");
        attributes = Arrays.copyOfRange(attributes, 1, attributes.length);
        return attributes;
    }

    private boolean isPrefixCorrect(String message) {
        return message.startsWith(this.prefix);
    }

    private String getCommand(String message) {
        String command = message.replaceFirst(this.prefix, "");
        var context = command.split(" ");
        return context[0];
    }
}
