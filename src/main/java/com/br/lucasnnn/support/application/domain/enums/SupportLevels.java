package com.br.lucasnnn.support.application.domain.enums;

import com.br.lucasnnn.support.infra.utils.Logging;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SupportLevels {

    public static final String LEVEL_1 = "JR";
    public static final String LEVEL_2 = "PL";
    public static final String LEVEL_3 = "SR";

    public SupportLevels() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static boolean isValid(String level) {
        var levels = getAll();

        return levels.contains(level);
    }

    public static List<String> getAll() {
        List<String> levels = new ArrayList<>();
        Field[] fields = SupportLevels.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType() == String.class) {
                try {
                    levels.add((String) field.get(null));
                } catch (IllegalAccessException e) {
                    Logging.error(e.getMessage(), e);
                }
            }
        }

        return levels;
    }
}