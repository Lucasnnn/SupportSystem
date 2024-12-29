package com.br.lucasnnn.support.application.domain.entity;

import java.lang.reflect.Field;

public abstract class BaseEntity {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" { ");

        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(this);
                sb.append(field.getName()).append("='").append(value).append("', ");
            } catch (IllegalAccessException e) {
                sb.append(field.getName()).append("='").append("ACCESS DENIED").append("', ");
            }
        }

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }

        sb.append(" } ");
        return sb.toString();
    }
}