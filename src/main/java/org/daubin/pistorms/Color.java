package org.daubin.pistorms;

import java.util.Map;

import org.daubin.pistorms.util.Enums;

import com.google.common.base.Supplier;

public enum Color implements Supplier<Integer> {
    NONE(0), 
    BLACK(1), 
    BLUE(2), 
    GREEN(3), 
    YELLOW(4), 
    RED(5), 
    WHITE(6), 
    BROWN(7), 
    UNKNOWN(666);

    private final static Map<Integer, Color> MAP = Enums.getMap(Color.values());

    private final int value;

    private Color(int value) {
        this.value = value;
    }

    public static Color get(int value) {
        Color c = MAP.get(value);
        return c == null ? UNKNOWN : c;
    }

    @Override
    public Integer get() {
        return value;
    }

}
