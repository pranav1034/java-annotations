package com.bridgelabz.javaannotations.beginnerproblems;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod{
    String level() default "HIGH";
}


class ApplyAnnotation {
    @ImportantMethod (level = "LOW")
    public void method1(){
        System.out.println("Method 1");
    }

    @ImportantMethod
    public void method2(){
        System.out.println("Method 2");
    }
}

public class MarkImportantMethods{
    public static void main(String[] args) {
        Class<ApplyAnnotation> clazz = ApplyAnnotation.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod impMethod = method.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Importance Level: " + impMethod.level());
            }
        }
    }
}


