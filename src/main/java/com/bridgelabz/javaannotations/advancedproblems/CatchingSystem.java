package com.bridgelabz.javaannotations.advancedproblems;
import java.util.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult{

}

class ExpensiveOperations {

    private static Map<String, Integer> cache = new HashMap<>();

    @CacheResult
    public int expensiveComputation(int number) {
        String key = "expensiveComputation:" + number;

        if (cache.containsKey(key)) {
            System.out.println("Returning from cache...");
            return cache.get(key);
        }

        System.out.println("Computing for " + number);
        int result = 0;
        for (int i = 1; i <= number * 1000000; i++) {
            result += i % 10;
        }

        cache.put(key, result);
        return result;
    }
}


public class CatchingSystem {
    public static void main(String[] args) throws Exception {
        ExpensiveOperations ops = new ExpensiveOperations();

        System.out.println("First Call: " + ops.expensiveComputation(5));
        System.out.println("Second Call: " + ops.expensiveComputation(5)); // Cached
        System.out.println("Different Input: " + ops.expensiveComputation(6)); // New computation
    }
}
