package com.snippets;

// Singleton
public class SimpleSingleton {
    private static SimpleSingleton singleInstance =  new SimpleSingleton();

    //Marking default constructor private
    //to avoid direct instantiation.
    private SimpleSingleton() {
    }

    //Get instance for class SimpleSingleton
    public static SimpleSingleton getInstance() {

        return singleInstance;
    }
}