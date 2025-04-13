package com.api.lambda;

public class Car {
    private String name;

    public Car() {}

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@FunctionalInterface
interface CreateCar {
    Car create(String name);
}