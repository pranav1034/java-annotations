package com.bridgelabz.javaannotations.advancedproblems;
import java.lang.reflect.Method;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed{
    String value();
}

class User{
    private String role;
    public User(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

class Admin{
    @RoleAllowed("Admin")
    public void performAdminTask(){
        System.out.println("Performing task");
    }
}

public class UserRole {
    public static void main(String[] args) {
        Admin admin = new Admin();
        User user = new User("User"); // Try changing to "Admin"

        for (Method method : Admin.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(RoleAllowed.class)) {
                RoleAllowed roleAllowed = method.getAnnotation(RoleAllowed.class);

                if (roleAllowed.value().equals(user.getRole())) {
                    try {
                        method.invoke(admin); // Invoke only if role matches
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Access Denied to method: " + method.getName());
                }
            }
        }
    }
}

