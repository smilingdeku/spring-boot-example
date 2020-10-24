package org.example.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtil {

    public static String message(String key, Object... args) {
        try {
            MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
            return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return key;
        }
    }

}
