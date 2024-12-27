package com.br.lucasnnn.support.application.domain.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SupportLevels {

    public static final String LEVEL_1 = "JR";
    public static final String LEVEL_2 = "PL";
    public static final String LEVEL_3 = "SR";

    public static boolean isValid(String level) {
        var levels = getAllSupportLevels();

        return levels.contains(level);
    }

    private SupportLevels() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    private static List<String> getAllSupportLevels() {
        List<String> levels = new ArrayList<>();
        Field[] fields = SupportLevels.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType() == String.class) {
                try {
                    levels.add((String) field.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return levels;
    }
}