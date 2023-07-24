package com.hd.enums;

/**
 * @author hdereli
 * @since 7/24/2023
 */
public enum EnumAddressType {
    HOME("Home"),
    WORK("Work"),
    OTHER("Other");

    private final String type;

    EnumAddressType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getDisplayValue() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
