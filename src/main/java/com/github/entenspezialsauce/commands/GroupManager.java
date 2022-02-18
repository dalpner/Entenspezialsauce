package com.github.entenspezialsauce.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GroupManager extends Command{
    public GroupManager(String invoker, String... alias) {
        super(invoker, alias);
    }

    @Override
    public void invoke(MessageReceivedEvent event, String... arguments) {
            var channel = event.getChannel();

    }

    public String getMessage(){

        return "";
    }
}
