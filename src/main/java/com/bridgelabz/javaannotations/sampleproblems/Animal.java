package com.bridgelabz.javaannotations.sampleproblems;

public class Animal {
    public void makeSound(){
        System.out.println("Animal makes sound");
    }
}
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}
class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();
    }
}
