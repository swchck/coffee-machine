package machine;

import machine.models.Cappuccino;
import machine.models.Coffee;
import machine.models.Espresso;
import machine.models.Latte;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static machine.Amounts.*;

public class CoffeeMachine {
    static final Scanner sc = new Scanner(System.in);
    static final String SORRY_TXT = "Sorry, not enough %s!\n";
    static Boolean isInterrupted = false;
    static Map<String, Integer> amounts = new HashMap<>(5);

    static {
        amounts.put(AMOUNT_OF_WATER.getName(), 400);
        amounts.put(AMOUNT_OF_MILK.getName(), 540);
        amounts.put(AMOUNT_OF_COFFEE.getName(), 120);
        amounts.put(DISPOSABLE_CUPS.getName(), 9);
        amounts.put(MONEY.getName(), 550);
    }

    public static void main(String[] args) {
        while (!isInterrupted) {
            menu();
        }
    }

    private static void menu() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        switch (sc.next()) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                remaining();
                break;
            case "exit":
                isInterrupted = !isInterrupted;
                break;
            default:
                System.out.println("Error!");
        }

    }

    public static void buy() {
        System.out.println(
                "What do you want to buy?\n" +
                        "1 - espresso,\n" +
                        "2 - latte,\n" +
                        "3 - cappuccino\n" +
                        "back - to main menu:"
        );
        switch (sc.next()) {
            case "1":
                cook(new Espresso());
                break;
            case "2":
                cook(new Latte());
                break;
            case "3":
                cook(new Cappuccino());
                break;
            case "back":
                menu();
                break;
            default:
                System.out.println("Wrong Input!");
                break;
        }
    }

    private static void take() {
        System.out.printf("I gave you $%d%n", amounts.get(MONEY.getName()));
        amounts.put(MONEY.getName(), 0);
    }

    public static void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        amounts.put(AMOUNT_OF_WATER.getName(), amounts.get(AMOUNT_OF_WATER.getName()) + sc.nextInt());
        System.out.println("Write how many ml of milk do you want to add:");
        amounts.put(AMOUNT_OF_MILK.getName(), amounts.get(AMOUNT_OF_MILK.getName()) + sc.nextInt());
        System.out.println("Write how many grams of coffee beans do you want to add:");
        amounts.put(AMOUNT_OF_COFFEE.getName(), amounts.get(AMOUNT_OF_COFFEE.getName()) + sc.nextInt());
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        amounts.put(DISPOSABLE_CUPS.getName(), amounts.get(DISPOSABLE_CUPS.getName()) + sc.nextInt());
    }

    private static void remaining() {
        System.out.printf(
                "The coffee machine has:\n" +
                        "%d of water\n" +
                        "%d of milk\n" +
                        "%d of coffee beans\n" +
                        "%d of disposable cups\n" +
                        "$%d of money\n",
                amounts.get(AMOUNT_OF_WATER.getName()),
                amounts.get(AMOUNT_OF_MILK.getName()),
                amounts.get(AMOUNT_OF_COFFEE.getName()),
                amounts.get(DISPOSABLE_CUPS.getName()),
                amounts.get(MONEY.getName())
        );
    }

    private static void cook(Coffee coffee) {
        var result = checkAmounts(coffee);
        if (result.equals("enough")) {
            System.out.println("I have enough resources, making you a coffee!");
            amounts.put(AMOUNT_OF_WATER.getName(), amounts.get(AMOUNT_OF_WATER.getName()) - coffee.getWater());
            amounts.put(AMOUNT_OF_COFFEE.getName(), amounts.get(AMOUNT_OF_COFFEE.getName()) - coffee.getCoffeeBeans());
            amounts.put(AMOUNT_OF_MILK.getName(), amounts.get(AMOUNT_OF_MILK.getName()) - coffee.getMilk());
            amounts.put(DISPOSABLE_CUPS.getName(), amounts.get(DISPOSABLE_CUPS.getName()) - 1);
            amounts.put(MONEY.getName(), amounts.get(MONEY.getName()) + coffee.getPrice());
        } else {
            System.out.printf(SORRY_TXT, result);
        }
    }

    private static String checkAmounts(Coffee coffee) {
        if (amounts.get(AMOUNT_OF_WATER.getName()) < coffee.getWater()) {
            return "water";
        } else if (amounts.get(AMOUNT_OF_COFFEE.getName()) < coffee.getCoffeeBeans()) {
            return "coffee beans";
        } else if (amounts.get(AMOUNT_OF_MILK.getName()) < coffee.getMilk()) {
            return "milk";
        } else if (amounts.get(DISPOSABLE_CUPS.getName()) < 1) {
            return "disposable cups";
        }
        return "enough";
    }

}
