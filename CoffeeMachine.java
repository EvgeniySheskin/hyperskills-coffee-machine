package machine;

import java.util.Scanner;

class ResourcesForCoffee {

    private int waterPerCup;
    private int milkPerCup;
    private int beansPerCup;

    public ResourcesForCoffee(int waterPerCup, int milkPerCup, int beansPerCup) {
        this.waterPerCup = waterPerCup;
        this.milkPerCup = milkPerCup;
        this.beansPerCup = beansPerCup;
    }

// updates the amounts of resources in the machine
    public void updateCMResources(int coffeePrice) {
        CoffeeMachineRes.water -= this.waterPerCup;
        CoffeeMachineRes.milk -= this.milkPerCup;
        CoffeeMachineRes.coffeeBeans -= beansPerCup;
        CoffeeMachineRes.cups -= 1;
        CoffeeMachineRes.money += coffeePrice;
    }
// estimates, is it possible to make the amount of coffee with the remaining resources
    public boolean estimateResources() {
        if (this.waterPerCup <= CoffeeMachineRes.water && this.milkPerCup <= CoffeeMachineRes.milk
                                                                && this.beansPerCup <= CoffeeMachineRes.coffeeBeans
                                                                                        && CoffeeMachineRes.cups != 0) {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        } else if (this.waterPerCup >= CoffeeMachineRes.water) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (this.milkPerCup >= CoffeeMachineRes.milk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (this.beansPerCup >= CoffeeMachineRes.coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        } else {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
    }
}
// making classes for each coffee
// we don't really need them right now, but I am sure we will need them on further stages
class ResourcesForEspresso extends ResourcesForCoffee {

    public ResourcesForEspresso(int waterPerCup, int milkPerCup, int beansPerCup) {
        super(waterPerCup, milkPerCup, beansPerCup);
    }
}

class ResourcesForLatte extends ResourcesForCoffee {

    public ResourcesForLatte(int waterPerCup, int milkPerCup, int beansPerCup) {
        super(waterPerCup, milkPerCup, beansPerCup);
    }
}

class ResourcesForCappuccino extends ResourcesForCoffee {

    public ResourcesForCappuccino(int waterPerCup, int milkPerCup, int beansPerCup) {
        super(waterPerCup, milkPerCup, beansPerCup);
    }
}

class InputHandling {
    private String inputLine;

    public InputHandling(Scanner in) {
        this.inputLine = in.nextLine();
    }

    public String getInputLine() {
        return inputLine;
    }
}

public class CoffeeMachine {
// a method to print out the machine resources left
    static void PrintOutMachineState() {
        System.out.println("The coffee machine has:");
        System.out.println(CoffeeMachineRes.water + " of water");
        System.out.println(CoffeeMachineRes.milk + " of milk");
        System.out.println(CoffeeMachineRes.coffeeBeans + " of coffee beans");
        System.out.println(CoffeeMachineRes.cups + " of disposable cups");
        System.out.println("$" + CoffeeMachineRes.money + " of money");
    }
// a method for buying coffee
    static void buyingCoffee(Scanner in) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        switch (new InputHandling(in).getInputLine()) {
            case "1":
                ResourcesForEspresso espresso = new ResourcesForEspresso(250, 0, 16);
                if (espresso.estimateResources()) {
                    espresso.updateCMResources(4);
                }
                break;
            case "2":
                ResourcesForLatte latte = new ResourcesForLatte(350, 75, 20);
                if (latte.estimateResources()) {
                    latte.updateCMResources(7);
                }
                break;
            case "3":
                ResourcesForCappuccino cappuccino = new ResourcesForCappuccino(200, 100, 12);
                if (cappuccino.estimateResources()) {
                    cappuccino.updateCMResources(6);
                }
                break;
            case "back":
                return;
            default:
                System.out.println("Unknown sort of coffee");
        }
    }
// a method for filling the machine
    static void fillingTheMachine(Scanner in) {
        System.out.println("Write how many ml of water do you want to add:");
        CoffeeMachineRes.water += Integer.parseInt(new InputHandling(in).getInputLine());
        System.out.println("Write how many ml of milk do you want to add:");
        CoffeeMachineRes.milk += Integer.parseInt(new InputHandling(in).getInputLine());
        System.out.println("Write how many grams of coffee beans do you want to add:");
        CoffeeMachineRes.coffeeBeans += Integer.parseInt(new InputHandling(in).getInputLine());
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        CoffeeMachineRes.cups += Integer.parseInt(new InputHandling(in).getInputLine());
    }
// a method for taking money away
    static void takingMoney() {
        System.out.println("I gave you " + CoffeeMachineRes.money);
        CoffeeMachineRes.money = 0;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean operate = true;
        while (operate) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            switch (new InputHandling(in).getInputLine()) {
                case "buy":
                    buyingCoffee(in);
                    break;
                case "fill":
                    fillingTheMachine(in);
                    break;
                case "take":
                    takingMoney();
                    break;
                case "remaining":
                    PrintOutMachineState();
                    break;
                case "exit":
                    operate = false;
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
}
// a class for machine resources storage
class CoffeeMachineRes {

    public static int water = 400;
    public static int milk = 540;
    public static int coffeeBeans = 120;
    public static int cups = 9;
    public static int money = 550;

}
