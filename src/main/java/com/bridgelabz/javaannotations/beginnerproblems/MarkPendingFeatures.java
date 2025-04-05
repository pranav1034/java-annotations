package com.bridgelabz.javaannotations.beginnerproblems;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

class TaskManager {
    @Todo(task = "Implement login feature", assignedTo = "Pranav", priority = "HIGH")
    public void loginFeature(){
        System.out.println("Login feature is implemented.");
    }

    @Todo(task = "Implement logout feature", assignedTo = "Aman")
    public void profilePage(){
        System.out.println("Profile page is implemented.");
    }

    @Todo(task = "Implement registration feature", assignedTo = "Nikhil", priority = "LOW")
    public void paymentPage(){
        System.out.println("Payment page is implemented.");
    }
}

public class MarkPendingFeatures {
    public static void main(String[] args) {
        Class<TaskManager> clazz = TaskManager.class;

        for(Method method: clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(Todo.class)){
                Todo todo = method.getAnnotation(Todo.class);
                System.out.println("Task: " + todo.task());
                System.out.println("Assigned To: " + todo.assignedTo());
                System.out.println("Priority: " + todo.priority());
            }
            System.out.println();
        }
    }
}
