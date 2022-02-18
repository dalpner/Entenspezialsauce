package com.github.entenspezialsauce.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Pong extends Command{

    public Pong() {
        super("ping", "p","EntenspezialsaucePing", "sendNudes");
    }

    @Override
    public void invoke(MessageReceivedEvent event, String... arguments) {
        var channel = event.getChannel();
        if(boolIsInt(arguments[0]))
            for (int i = 0; i < Integer.parseInt(arguments[0]); i++){
                channel.sendMessage("Pong!").queue();
            }
    }

    private boolean boolIsInt(String arg){
        try{
            Integer.valueOf(arg);
            return true;
        }catch (NumberFormatException nfe){
            System.out.println("Hahahaha da war wer inkompetent");
            return false;
        }
    }
}
