package com.github.entenspezialsauce;

import com.github.entenspezialsauce.commands.Command;
import com.github.entenspezialsauce.commands.Help;
import com.github.entenspezialsauce.commands.Pong;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;

public class EntenListener extends ListenerAdapter {

    private final String prefix = "!";
    private final List<Command> commands = List.of(new Pong(), new Help());

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String content = message.getContentRaw();
        String commandString = content.trim();

        if (!this.isPrefixCorrect(commandString)) return;

        var attribute = this.getAttributes(commandString);
        var commandInvoker = attribute[0];
        var arguments = Arrays.copyOfRange(attribute, 1, attribute.length);

        var commandOptional = this.commands.stream()
                .filter((command) -> command.isMe(commandInvoker))
                .findAny();
        if (commandOptional.isEmpty()) return;
        commandOptional.ifPresentOrElse((command) -> command.invoke(event, arguments), () -> this.noCommand(event.getChannel()));
    }

    private String[] getAttributes(String message) {
        return message.replaceFirst(this.prefix, "").split(" ");
    }

    private boolean isPrefixCorrect(String message) {
        return message.startsWith(this.prefix);
    }

    private void noCommand(MessageChannel channel) {
        channel.sendMessage("I'm am not that kind of duck").queue();
    }
    public String getPrefix() {
        return this.prefix;
    }
    public List<Command> getCommands() {
        return this.commands;
    }

}
