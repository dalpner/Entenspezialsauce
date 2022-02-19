package com.github.entenspezialsauce.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class PongCommand extends Command{

    private final String gifUrl = "https://c.tenor.com/WqFUaRFxipoAAAAC/duck-duckit.gif";

    public PongCommand() {
        super("ping", "p","EntenspezialsaucePing", "sendNudes");
    }


    @Override
    public void invoke(MessageReceivedEvent event, String... arguments) {
        var channel = event.getChannel();
        if(this.boolIsInt(arguments[0]))
            if (Integer.parseInt(arguments[0]) > 10){
                channel.sendMessage("We don't do this here.").queue();
                channel.sendMessage(this.gifUrl).queue();
                return;
            }
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
