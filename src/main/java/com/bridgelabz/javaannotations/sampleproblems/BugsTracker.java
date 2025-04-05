package com.bridgelabz.javaannotations.sampleproblems;
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports{
    BugReport[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport{
    String description();
}

class Bugs{

    @BugReport(description = "Null pointer exception")
    @BugReport(description = "Array index out of bounds")
    @BugReport(description = "Database connection error")
    public void analyzeBugs(){
        System.out.println("Analyzing bugs...");
    }
}

public class BugsTracker {
    public static void main(String[] args) {
        Class<Bugs> clazz = Bugs.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BugReport.class) || method.isAnnotationPresent(BugReports.class)) {
                BugReport[] bugReports = method.getAnnotationsByType(BugReport.class);

                System.out.println("Method: " + method.getName());
                for (BugReport bug : bugReports) {
                    System.out.println("  - Bug: " + bug.description());
                }
            }
        }

    }
}
