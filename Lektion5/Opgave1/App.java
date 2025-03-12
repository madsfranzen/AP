package Opgave1;

import java.util.HashSet;

public class App {
    public static void main(String[] args) {
        Car car1 = new Car(123456, "Toyota", "Corolla", "Red");
        Car car2 = new Car(654321, "Ford", "Mustang", "Blue");
        Car car3 = new Car(112233, "Chevrolet", "Camaro", "Black");

        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car3); 
        System.out.println();

        HashSet<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);

        System.out.println("\nCARS:");
        for (Car car : cars) {
            System.out.println(car);
        }

        Car car4 = new Car(123456, "Toyota", "Corolla", "Red");
        Car car5 = new Car(654321, "Ford", "Mustang", "Blue");
        Car car6 = new Car(112233, "Chevrolet", "Camaro", "Black");

        cars.add(car4);
        cars.add(car5);
        cars.add(car6);

        System.out.println("\nCARS:");
        for (Car car : cars) {
            System.out.println(car);
        }
    }
    
}
