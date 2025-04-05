package com.bridgelabz.javaannotations.sampleproblems;
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface taskInfo {
    String priority();
    String assignedTo();
}

public class Tasks {

    @taskInfo(priority = "High",assignedTo = "Pranav")
    public void firstTask(){
        System.out.println("This is the first task");
    }

    @taskInfo(priority = "Low",assignedTo = "Pranav")
    public void secondTask(){
        System.out.println("This is the second task");
    }
}

class CustomAnnotations{
    public static void main(String[] args) {
        Class<Tasks> tasksClass =Tasks.class;
        Method[] methods = tasksClass.getDeclaredMethods();

        for(Method method : methods){
            if(method.isAnnotationPresent(taskInfo.class)){
                taskInfo info = method.getAnnotation(taskInfo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Priority: " + info.priority());
                System.out.println("Assigned To: " + info.assignedTo());
            }
            System.out.println();
        }
    }
}
