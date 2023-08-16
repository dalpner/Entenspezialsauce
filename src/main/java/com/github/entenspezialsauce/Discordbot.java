package com.github.entenspezialsauce;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Discordbot {
    public static void main(String[] arguments) throws Exception {
        JDA api = JDABuilder.createDefault("<bot-token>").build();
        api.addEventListener(new EntenListener());
    }
}
