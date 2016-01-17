package ru.zignet.plugins.telegrambot.user;

/**
 * @autor Tengiz Sharafiev <btolfa@gmail.com>
 * @date 2016-01-17
 */

import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import hudson.Extension;
import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;

@ExportedBean(defaultVisibility = 999)
public class TelegramUserProperty extends UserProperty {

    @Extension
    public static final TelegramUserPropertyDescriptor DESCRIPTOR = new TelegramUserPropertyDescriptor();

    private String username;

    public TelegramUserProperty() {
        // public constructor needed for @Extension parsing
    }

    public TelegramUserProperty(final String username) {
        if ((username != null) && (! "".equals(username)) && (! validateChatid(username))) {
            throw new IllegalArgumentException("malformed Telegram Username " + username);
        }

        if ("".equals(username)) {
            this.username = null;
        } else {
            this.username = username;
        }
    }

    @Exported
    public String getUsername() {
        return this.username;
    }

    @Override
    public UserPropertyDescriptor getDescriptor() {
        return DESCRIPTOR;
    }

    /* https://telegram.org/faq#q-what-can-i-use-as-my-username
     * Q: What can I use as my username?
     * You can use a-z, 0-9 and underscores. Usernames are case-insensitive, but Telegram will store your capitalization
     * preferences (e.g. Telegram and TeleGram is the same user). The username must be at least five characters long.
     *
     */
    private static final boolean validateChatid(final String chatid) {
        return (chatid.length() >= 5) && chatid.matches("[a-zA-Z0-9_]+");
    }

}
