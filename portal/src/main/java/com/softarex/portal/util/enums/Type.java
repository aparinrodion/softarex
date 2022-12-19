package com.softarex.portal.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum Type {
    SINGLE_LINE_TEXT("Single line text"),
    MULTILINE_TEXT("Multiline text"),
    RADIO_BUTTON("Radio button"),
    CHECKBOX("Checkbox"),
    COMBOBOX("Combobox"),
    DATE("Date");

    private final String info;
    private static final Map<String, Type> lookup = new HashMap<>();

    static {
        for (Type type : Type.values()) {
            lookup.put(type.getInfo(), type);
        }
    }

    public static Type get(String info) {
        return lookup.get(info);
    }
}
