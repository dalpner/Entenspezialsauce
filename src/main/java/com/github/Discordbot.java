package com.github;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Discordbot {
    public static void main(String[] arguments) throws Exception {
        JDA api = JDABuilder.createDefault("OTQzNTc3NjU3MDg0MzY2ODc5.Yg1FLA.EmGDinG9IR3dhlLtnrf59G6yayU").build();
        api.addEventListener(new EntenListener());
    }
}
