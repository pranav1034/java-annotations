package com.bridgelabz.javaannotations.sampleproblems;
import java.util.ArrayList;

public class Suppress {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("Hello");
        list.add(100);
        for(Object obj : list) {
            System.out.println(obj);
        }
    }
}
