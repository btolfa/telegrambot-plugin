package ru.zignet.plugins.telegrambot.user;

/**
 * @autor Tengiz Sharafiev <btolfa@gmail.com>
 * @date 2016-01-17
 */

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.Exported;

import hudson.model.UserProperty;
import org.kohsuke.stapler.export.ExportedBean;

@ExportedBean(defaultVisibility = 999)
public class TelegramUserProperty extends UserProperty {
    private String username;

    public TelegramUserProperty() {
    }

    @DataBoundConstructor
    public TelegramUserProperty(final String username) {
        this.username = username;
    }

    @Exported
    public String getUsername() {
        return this.username;
    }
}
