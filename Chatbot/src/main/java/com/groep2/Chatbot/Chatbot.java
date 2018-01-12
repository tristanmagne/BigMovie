//Mainpackage jar;
package com.groep2.Chatbot;

import com.rivescript.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Chatbot extends TelegramLongPollingBot {
    private RiveScript bot = new RiveScript(Config.utf8());


    Chatbot() {
        bot.loadDirectory("Chatbot/src/main/resources/rivescript");
        bot.sortReplies();
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            // Get reply
            String reply = bot.reply(String.valueOf(chat_id), message_text);

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(reply);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Movie Facts";
    }

    @Override
    public String getBotToken() {
        return "535298953:AAFKoLil3IAE__rjscmxbG1EYeefDJin3BM";
    }
}
