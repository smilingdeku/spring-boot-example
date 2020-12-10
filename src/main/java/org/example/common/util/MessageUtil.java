package org.example.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 消息工具类
 */
public class MessageUtil {

    /**
     * 获取消息值
     *
     * @param key  消息键
     * @param args 消息参数
     * @return 消息值
     */
    public static String get(String key, Object... args) {
        try {
            MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
            return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return key;
        }
    }

}
