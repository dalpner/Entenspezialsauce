package com.github.entenspezialsauce.commands;

import com.github.entenspezialsauce.NumberEmotes;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

@Command.Info(
        value = "groupMaster",
        description = "Create your own roles and channels for a groupproject",
        usage = "!groupmaster group1 groupn <Message>",
        aliases = {"gm", "teamarbeit"}
)
public class GroupMasterCommand extends Command {

    @Override
    public void invoke(MessageReceivedEvent event, String... arguments) {
        var channel = event.getChannel();
        var groupMasterBuilder = new EmbedBuilder();
        groupMasterBuilder.setColor(new Color(154, 61, 21));
        groupMasterBuilder.setTitle("I created new groups for your project.");
        var messageIndex = 0;
        for (String argument : arguments) {
            if (!argument.startsWith("\"")) messageIndex++;
        }
        for (int i = 0; i < messageIndex - 1; i++) {
            groupMasterBuilder
                    .appendDescription(NumberEmotes.emoteMap.get(i + 1))
                    .appendDescription(" : ")
                    .appendDescription(arguments[i])
                    .appendDescription("\n \n");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = messageIndex - 1; i < arguments.length; i++) {
            stringBuilder.append(arguments[i].replace("\"","")).append(" ");
        }
        groupMasterBuilder
                .addBlankField(false)
                .addField("Message:", stringBuilder.toString(), false);
        channel.sendMessageEmbeds(groupMasterBuilder.build()).queue();
    }
}
