package com.bridgelabz.javaannotations.sampleproblems;

public class LegacyAPI {
    @Deprecated
    public void oldFeature(){
        System.out.println("This is an old feature");
    }
    public void newFeature(){
        System.out.println("This is a new feature");
    }

    public static void main(String[] args) {
        LegacyAPI api = new LegacyAPI();
        api.oldFeature(); // This will show a warning
        api.newFeature(); // This is the recommended way
    }
}
