package com.github.entenspezialsauce.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Message;
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
                    .appendDescription(Emoji.fromUnicode("U+" + Integer.toHexString(129360 + i)).getName())
                    .appendDescription(" : ")
                    .appendDescription(arguments[i])
                    .appendDescription("\n \n");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = messageIndex - 1; i < arguments.length; i++) {
            stringBuilder.append(arguments[i].replace("\"", "")).append(" ");
        }

        groupMasterBuilder
                .addBlankField(false)
                .addField("Message:", stringBuilder.toString(), false);
        int finalMessageIndex = messageIndex;
        channel.sendMessageEmbeds(groupMasterBuilder.build()).queue((message) -> this.addReactions(message, finalMessageIndex));
    }


    public void addReactions(Message message, Integer length) {
        for (int i = 0; i < length - 1; i++) {
            var myUnicode = "U+" + Integer.toHexString(129360 + i);
            message.addReaction(myUnicode).queue();
        }
    }
}
