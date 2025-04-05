package com.bridgelabz.javaannotations.intermediateproblems;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime{
}

class Tasks {
    @LogExecutionTime
    public void firstTask(){
        double sum =0;
        for(int i=0;i<100000;i++){
            sum+=Math.pow(i,3);
        }
    }

    @LogExecutionTime
    public void secondTask(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<10000;i++){
            sb.append(i).reverse();
        }
        //System.out.println(sb.toString());
    }
}

public class MeasureExecutionTime {
    public static void main(String[] args) {
        Tasks tasks = new Tasks();
        Class<Tasks> clazz = Tasks.class;

        for(Method method : clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(LogExecutionTime.class)){
                long start = System.nanoTime();
                try {
                    method.invoke(tasks);
                }
                catch (IllegalAccessException | InvocationTargetException e){
                    e.printStackTrace();
                }
                long end = System.nanoTime();
                long duration = end-start;
                System.out.println("Method: "+method.getName() +" took time "+ duration/1000000 + " ms");
            }
        }
    }
}

