package com.bridgelabz.javaannotations.advancedproblems;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

class User{
    @JsonField(name = "user_name")
    private String name;

    @JsonField(name = "user_id")
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class JsonConverter {
    public static String convertToJson(Object obj) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        Field[] fields = obj.getClass().getDeclaredFields();
        boolean firstField = true;

        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class)) {
                field.setAccessible(true);
                JsonField annotation = field.getAnnotation(JsonField.class);

                try {
                    Object value = field.get(obj);

                    if (!firstField) {
                        json.append(", ");
                    }
                    json.append("\"").append(annotation.name()).append("\": ");

                    if (value instanceof String) {
                        json.append("\"").append(value).append("\"");
                    } else {
                        json.append(value);
                    }

                    firstField = false;

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        json.append("}");
        return json.toString();
    }
}

public class ObjectToJson {
    public static void main(String[] args) {
        User user =new User("Pranav",22);
        String json = JsonConverter.convertToJson(user);
        System.out.println(json);
    }
}
