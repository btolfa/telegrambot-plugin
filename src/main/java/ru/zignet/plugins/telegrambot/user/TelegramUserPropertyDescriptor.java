package ru.zignet.plugins.telegrambot.user;

/**
 * @author Tengiz Sharafiev <btolfa@gmail.com>
 * @date 2016-01-17
 */

import hudson.model.User;
import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;

import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Descriptor for Telegram user property.
 */
public class TelegramUserPropertyDescriptor extends UserPropertyDescriptor {

    public static final String PARAMETERNAME_USERNAME = "telegram.user.username";

    public TelegramUserPropertyDescriptor() {
        super(TelegramUserProperty.class);
    }

    @Override
    public UserProperty newInstance(User user) {
        return new TelegramUserProperty(null);
    }

    @Override
    public UserProperty newInstance(StaplerRequest req, JSONObject formData) throws FormException {
        try {
            return new TelegramUserProperty(req.getParameter(PARAMETERNAME_USERNAME));
        } catch (IllegalArgumentException e) {
            throw new FormException("invalid Telegram Username", PARAMETERNAME_USERNAME);
        }
    }

    @Override
    public String getDisplayName() {
        return "Telegram Username";
    }
}
