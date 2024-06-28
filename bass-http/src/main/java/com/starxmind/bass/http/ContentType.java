package com.starxmind.bass.http;

import lombok.Getter;

/**
 * Http media type
 *
 * @author Xpizza
 * @since shire1.0
 */
@Getter
public enum ContentType {
    /**
     * JSON media type
     */
    JSON("application/json; charset=utf-8"),

    /**
     * Form media type
     */
    FORM("application/x-www-form-urlencoded; charset=utf-8");

    /**
     * A well formed media type for string
     */
    private final String wellFormedMediaTypeString;

    ContentType(String wellFormedMediaTypeString) {
        this.wellFormedMediaTypeString = wellFormedMediaTypeString;
    }
}
