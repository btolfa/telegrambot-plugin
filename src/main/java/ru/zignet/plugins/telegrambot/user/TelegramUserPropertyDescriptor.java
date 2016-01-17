package ru.zignet.plugins.telegrambot.user;

/**
 * @author Tengiz Sharafiev <btolfa@gmail.com>
 * @date 2016-01-17
 */

import hudson.Extension;
import hudson.model.User;
import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;

import hudson.util.FormValidation;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Descriptor for Telegram user property.
 */
@Extension
public class TelegramUserPropertyDescriptor extends UserPropertyDescriptor {

    public TelegramUserPropertyDescriptor() {
        super(TelegramUserProperty.class);
    }

    @Override
    public String getDisplayName() {
        return "Telegram Username";
    }

    @Override
    public UserProperty newInstance(User user) {
        return new TelegramUserProperty(null);
    }

    @Override
    public UserProperty newInstance(StaplerRequest req, JSONObject formData) throws FormException {
        return req.bindJSON(TelegramUserProperty.class, formData);
    }

    public FormValidation doCheckUsername(@QueryParameter String value) throws IOException, ServletException {
        if (value.length() == 0) {
            return FormValidation.error("Please set a username");
        }
        if (value.length() < 5) {
            return FormValidation.error("The username must be at least five characters long");
        }
        if (! value.matches("[a-zA-Z0-9_]+")) {
            return FormValidation.error("You must use a-z, 0-9 and underscores");
        }

        return FormValidation.ok();
    }
}
