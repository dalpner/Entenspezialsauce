package com.github.entenspezialsauce.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

public abstract class Command {
    public abstract void invoke(MessageReceivedEvent event, String... arguments);

    public String getInvoker() {
        return this.getClass().getAnnotation(Command.Info.class).value();
    }

    public String getDescription() {
        return this.getClass().getAnnotation(Command.Info.class).description();
    }

    public String getUsage() {
        return this.getClass().getAnnotation(Command.Info.class).usage();
    }

    public String[] getAliases() {
        return this.getClass().getAnnotation(Command.Info.class).aliases();
    }

    public boolean isMe(String duckblood) {
        return this.getInvoker().equalsIgnoreCase(duckblood) || Arrays.stream(this.getAliases()).anyMatch(af -> af.equalsIgnoreCase(duckblood));
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface Info {
        String value();

        String description() default "";

        String usage() default "";

        String[] aliases() default {};
    }
}
