package com.softarex.portal.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum Type {
    SINGLE_LINE_TEXT("single line text"),
    MULTILINE_TEXT("multiline text"),
    RADIO_BUTTON("radio button"),
    CHECKBOX("checkbox"),
    COMBOBOX("combobox"),
    DATE("date");

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
