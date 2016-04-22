package com.yj.intranet.lampcontroller.platform.filter;

import com.yj.core.log.LogMessageFilter;
import com.yj.core.platform.web.filter.PlatformFilter;

/**
 * @author mort
 */
public class CommonLogMessageFilter extends LogMessageFilter {

    static final String PLATFORM_FILTER_LOGGER = PlatformFilter.class.getName();

    @Override
    public String filter(String loggerName, String message) {
        if (PLATFORM_FILTER_LOGGER.equals(loggerName)) {
            String filteredMessage = message;
            filteredMessage = filterRequestParams(filteredMessage);
            return filteredMessage;
        }
        return message;
    }

    private String filterRequestParams(String message) {
        if (message.startsWith("[param] password=")) return mask(message, "password=(.*)");
        if (message.startsWith("[param] confirmPassword=")) return mask(message, "confirmPassword=(.*)");
        if (message.startsWith("[param] cardNumber=")) return mask(message, "cardNumber=(.*)");
        if (message.startsWith("[param] currentPassword=")) return mask(message, "currentPassword=(.*)");
        if (message.startsWith("[param] newPassword=")) return mask(message, "newPassword=(.*)");
        if (message.startsWith("[param] verifyPassword=")) return mask(message, "verifyPassword=(.*)");
        return message;
    }
}
