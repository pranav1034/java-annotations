package com.bridgelabz.javaannotations.intermediateproblems;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength{
    int value();
}


class User{

    @MaxLength(10)
    private String userName;

    public User(String userName) {
        this.userName = userName;

        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                field.setAccessible(true);
                try {
                    Object value = field.get(this);
                    if (value instanceof String) {
                        String str = (String) value;
                        if (str.length() > maxLength.value()) {
                            throw new IllegalArgumentException("Field '" + field.getName()
                                    + "' exceeds max length of " + maxLength.value());
                        }
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class FieldValidation {
    public static void main(String[] args) {
        User user1= new User("Pranav");
        User user2= new User("Abcdefghijklmn");
    }
}
