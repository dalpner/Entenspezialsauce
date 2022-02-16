package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

   private final String invoker;
   private final String[] alias;

    public Command(String invoker, String... alias){
        this.invoker = invoker;
        this.alias = alias;
    }

    public abstract void invoke(MessageReceivedEvent event, String... arguments);

    public String getInvoker() {
        return this.invoker;
    }

    public String[] getAlias() {
        return alias;
    }
}
