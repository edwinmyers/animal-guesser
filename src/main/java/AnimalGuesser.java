package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnimalGuesser {

    static Map<String, String> animals;
    private static final Scanner scanner = new Scanner(System.in);
    private static String lastGuess;

    public static void main(String[] args) throws InterruptedException {
        initAnimals();
        greet();
        while (true) {
            if (!tryGuess()) {
                learnNewAnimal(lastGuess);
            }
            if (promptExit()) {
                System.exit(1);
            }
            greet();
        }
    }
    private static boolean tryGuess() throws InterruptedException {
        String usersAnswer;
        for (Map.Entry<String, String> animal : animals.entrySet()) {
            askUser(animal.getValue());
            usersAnswer = scanner.nextLine();
            lastGuess = animal.getKey();
            while (!checkInput(usersAnswer)) {
                usersAnswer = scanner.nextLine();
            }
            if (usersAnswer.equals("да")) {
                System.out.println("Это животное " + lastGuess + "!");
                Thread.sleep(1000);
                return true;
            }
        }
        return false;
    }
    private static void learnNewAnimal(String lastGuess) throws InterruptedException {
        System.out.println("Какое животное ты загадал?");
        String userGuess = scanner.nextLine();
        Thread.sleep(1000);
        System.out.println("Чем " + userGuess + " отличается от " + lastGuess + "?");
        String answer = scanner.nextLine();
        animals.put(userGuess, answer);
    }

    private static boolean checkInput(String usersAnswer) {
        if (!usersAnswer.equals("да") && !usersAnswer.equals("нет")) {
            System.out.println("Ответь да или нет");
            return false;
        }
        return true;
    }

    private static void askUser(String animalProperty) {
        System.out.println("Это животное " + animalProperty + "?");
    }

    private static void greet() throws InterruptedException {
        System.out.println("Загадай животное, а я попробую угадать...");
        Thread.sleep(1000);
    }

    private static boolean promptExit() {
        System.out.println("Хочешь еще поиграть? (да/нет)");
        String input = scanner.nextLine();
        checkInput(input);
        return input.equals("нет");
    }
    private static void initAnimals() {
        animals = new HashMap<>();
        animals.put("кит", "живет в океане");
        animals.put("кот", "живет на суше");
    }
}
