package dev.vanderloureiro;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {

    public static void main(String[] args) {
        System.out.println(":::::::::: Starting relembrar-me ::::::::::");
        Quarkus.run(args);
    }
}
