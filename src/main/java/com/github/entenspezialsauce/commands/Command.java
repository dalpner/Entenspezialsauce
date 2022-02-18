package com.github.entenspezialsauce.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;

public abstract class Command {

    private final String invoker;
    private final String[] alias;

    public Command(String invoker, String... alias) {
        this.invoker = invoker;
        this.alias = alias;
    }

    public abstract void invoke(MessageReceivedEvent event, String... arguments);

    public String getInvoker() {
        return this.invoker;
    }

    public String[] getAlias() {
        return this.alias;
    }

    public boolean isMe(String duckblood) {
        return this.invoker.equalsIgnoreCase(duckblood) || Arrays.stream(this.alias).anyMatch(af -> af.equalsIgnoreCase(duckblood));
    }

}
