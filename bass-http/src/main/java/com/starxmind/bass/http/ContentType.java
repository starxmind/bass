package com.starxmind.bass.http;

/**
 * Http media type
 *
 * @author Xpizza
 * @since shire1.0
 */
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
    private String wellFormedMediaTypeString;

    public String getWellFormedMediaTypeString() {
        return wellFormedMediaTypeString;
    }

    ContentType(String wellFormedMediaTypeString) {
        this.wellFormedMediaTypeString = wellFormedMediaTypeString;
    }
}
