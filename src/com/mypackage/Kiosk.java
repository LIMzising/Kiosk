package com.mypackage;

import java.util.Scanner;

public class Kiosk
{
    private Customer customer;
    private LuckyDipGenerator randomNumberGenerator;

    public Kiosk()
    {
        customer = new Customer();
        randomNumberGenerator = new LuckyDipGenerator(5);

        start();
    }

    public void buyCredit()
    {
        Scanner console = new Scanner(System.in);
        boolean validCredit = false;
        while (!validCredit)
        {
            System.out.print("How many credits you wish to buy: $");
            String creditPurchaseInput = console.nextLine().trim();
            if (isValidNumber(creditPurchaseInput))
            {
                int creditPurchase = Integer.parseInt(creditPurchaseInput);
                if (creditPurchase <= 0 || creditPurchase > 1000)
                    System.out.println("Please enter values between 0 " +
                            "(not inclusive) and 1000");
                else
                {
                    validCredit = true;
                    customer.setCreditBalance(creditPurchase, 0);
                }
            }
            else
            {
                System.out.println("Error: Please enter valid number, e.g. 50");
                System.out.println("Please do not enter negative value " +
                        "or decimal points");
            }
        }
    }

    public void checkOutOrder()
    {
        currentOrderStatus();
        System.out.println("Thank you for shopping with us. Please collect your ");
        System.out.println("goods (and your balance) at the front desk.");
        customer = new Customer();
    }

    public void currentOrderStatus()
    {
        String name = customer.getName();
        String totalItems = customer.getTotalItems();
        int totalCosts = customer.getTotalCosts();
        int creditBalance = customer.getCreditBalance();
        if (totalItems.length() > 0)
        {
            System.out.println("Customer " + name + " has purchased these items:");
            System.out.println(totalItems);
            System.out.println("worth a total of $" + totalCosts);
        }
        else
            System.out.println("Customer " + name + " has not purchased any " +
                    "item so far");
        System.out.println("Credit balance: $" + creditBalance);
    }

    public void displayHelp()
    {
        System.out.println("This kiosk is designed for the customers to purchase ");
        System.out.println("items. There are 7 options available on the menu.");
        System.out.println();
        System.out.println("You must create an order (Option 1), by providing ");
        System.out.println("your name and entering some credits before further ");
        System.out.println("proceeding.");
        System.out.println();
        System.out.println("Ensure you have sufficient balance on account, if ");
        System.out.println("not you could purchase more credits (Option 2) to ");
        System.out.println("continue.");
        System.out.println();
        System.out.println("After creating an order and ensuring your balance, ");
        System.out.println("you could start off your shopping (Option 3). While ");
        System.out.println("there are only 5 items available in this kiosk, you ");
        System.out.println("can also ask the kiosk to randomly pick one of ");
        System.out.println("the 5 items to purchase (Item 6)");
        System.out.println();
        System.out.println("This kiosk will also keep a record of all the ");
        System.out.println("items you have purchased and the total cost of ");
        System.out.println("the items. You can print out to see your current ");
        System.out.println("order statistics (Option 4).");
        System.out.println();
        System.out.println("After you finish with your order and wish to collect ");
        System.out.println("the items purchased, check out your order (Option 5).");
        System.out.println();
        System.out.println("If you wish to know about how to use this kiosk, ");
        System.out.println("display help (Option 6) to display some brief ");
        System.out.println("instructions.");
        System.out.println();
        System.out.println("This kiosk will continue displaying the menu ");
        System.out.println("unless you exit the program (Option 7).");
    }

    public void displayItem()
    {
        System.out.println("These are the items available for sale today: ");
        System.out.println("---------------------------------------------");
        System.out.println("(1) PEN, worth $10.");
        System.out.println("(2) BOOK, worth $20.");
        System.out.println("(3) DVD, worth $30.");
        System.out.println("(4) MOUSE, worth $40.");
        System.out.println("(5) KEYBOARD, worth $50.");
        System.out.println("(6) Let ME pick a random item for you!!!");
        System.out.println("");
        System.out.print("Pick a number between 1-6: ");
    }

    public void displayMenu()
    {
        System.out.println();
        System.out.println("Welcome to Super Fantastic Kiosk");
        System.out.println("================================");
        System.out.println("(1) Create A New Order");
        System.out.println("(2) Buy More Credit");
        System.out.println("(3) Purchase An Item");
        System.out.println("(4) What Have I Ordered So Far?");
        System.out.println("(5) Collect My Order");
        System.out.println("(6) Display Help");
        System.out.println("(7) Leave Kiosk");
        System.out.print("Choose an option: ");
    }

    public int getItemCost(int itemNumber)
    {
        int itemCost = 0;
        switch (itemNumber)
        {
            case 1:
                itemCost = 10;
                break;
            case 2:
                itemCost = 20;
                break;
            case 3:
                itemCost = 30;
                break;
            case 4:
                itemCost = 40;
                break;
            case 5:
                itemCost = 50;
                break;
        }
        return itemCost;
    }

    public String getItemName(int itemNumber)
    {
        String itemName ="";
        switch (itemNumber)
        {
            case 1:
                itemName = "PEN";
                break;
            case 2:
                itemName = "BOOK";
                break;
            case 3:
                itemName = "DVD";
                break;
            case 4:
                itemName = "MOUSE";
                break;
            case 5:
                itemName = "KEYBOARD";
                break;
        }
        return itemName;
    }

    public int getItemNumber()
    {
        Scanner console = new Scanner(System.in);
        String itemOptionInput = console.nextLine().trim();
        int itemNumber = 0;
        if (isValidNumber(itemOptionInput))
        {
            int itemOption = Integer.parseInt(itemOptionInput);
            if (itemOption >= 1 && itemOption<= 5)
            {
                itemNumber = itemOption;
            }
            else if (itemOption == 6)
            {
                int creditBalance = customer.getCreditBalance();
                int randomItemNumber = 0;

                int itemCost = 0;
                if (creditBalance < minimumItemCost())
                {
                    System.out.println("Sorry, you do not have enough credit " +
                            "to purchase any item!");
                    System.out.println("Credit Balance: $" + creditBalance + ", ");
                    System.out.println("Minimum Item Cost: $" + minimumItemCost());
                }
                else
                {
                    do
                    {
                        randomItemNumber = randomNumberGenerator.randomNumber();
                        itemCost = getItemCost(randomItemNumber);
                    } while (creditBalance < itemCost);
                    itemNumber = randomItemNumber;
                    System.out.println("Item " + itemNumber + " is picked!");
                }
            }
            else
                System.out.println("Error: your choice must be between 1-6");
        }
        else
            System.out.println("Error: Please enter valid number");
        return itemNumber;
    }

    public boolean isValidNumber(String userInput)
    {
        if (userInput.length() == 0)
            return false;
        if (userInput.length() > 10)
            return false;
        int index = 0;
        while (index < userInput.length())
        {
            char thisCharacter = userInput.charAt(index);
            if (!Character.isDigit(thisCharacter))
                return false;
            index++;
        }
        return true;
    }

    public boolean isValidOption()
    {
        String name = customer.getName();
        if (name.length() == 0)
            return false;
        else
            return true;
    }

    public int minimumItemCost()
    {
        int itemNumber = 1;
        int minimumItemCost = getItemCost(itemNumber);
        while (itemNumber <= 5)
        {
            int itemCost = getItemCost(itemNumber);
            if (itemCost < minimumItemCost)
                minimumItemCost = itemCost;
            itemNumber++;
        }
        return minimumItemCost;
    }

    public void newOrder()
    {
        customer = new Customer();
        Scanner console = new Scanner(System.in);
        boolean validName = false;
        while (!validName)
        {
            System.out.print("Enter your name please: ");
            String name = console.nextLine().trim();
            if (name.length() > 0)
            {
                validName = true;
                customer.setName(name);
            }
            else
                System.out.println("Please enter valid name");
        }
        System.out.println("Buy some credits please!");
        buyCredit();
    }

    public void purchase()
    {
        int itemNumber = getItemNumber();
        if (itemNumber != 0)
        {
            String itemName = getItemName(itemNumber);
            int itemCost = getItemCost(itemNumber);
            int creditBalance = customer.getCreditBalance();
            if (creditBalance >= itemCost)
            {
                customer.setCreditBalance(0, itemCost);
                customer.setTotalItems(itemName);
                customer.setTotalCosts(itemCost);
                creditBalance = customer.getCreditBalance();
                System.out.println("You have bought a " + itemName + ", " +
                        "worth $" + itemCost);
                System.out.println("Your new credit balance is now: $" +
                        creditBalance);
            }
            else
            {
                System.out.println("Sorry, you do not have enough credit to " +
                        "purchase this item!");
                System.out.print("Credit Balance: $" + creditBalance + ", ");
                System.out.println("Item Cost: $" + itemCost);
            }
        }
    }

    public void start()
    {
        Scanner console = new Scanner(System.in);
        boolean exit = false;
        while (!exit)
        {
            displayMenu();
            String invalidOption = "Please create a new order (Option 1) first";
            String optionInput = console.nextLine().trim();
            if (isValidNumber(optionInput))
            {
                int option = Integer.parseInt(optionInput);
                switch (option)
                {
                    case 1:
                        newOrder();
                        break;
                    case 2:
                        if (isValidOption())
                            buyCredit();
                        else
                            System.out.println(invalidOption);
                        break;
                    case 3:
                        if (isValidOption())
                        {
                            displayItem();
                            purchase();
                        }
                        else
                            System.out.println(invalidOption);
                        break;
                    case 4:
                        if (isValidOption())
                            currentOrderStatus();
                        else
                            System.out.println(invalidOption);
                        break;
                    case 5:
                        if (isValidOption())
                            checkOutOrder();
                        else
                            System.out.println(invalidOption);
                        break;
                    case 6:
                        displayHelp();
                        break;
                    case 7:
                        exit = true;
                        customer = new Customer();
                        break;
                    default:
                        System.out.println("Error: your choice must be " +
                                "between 1-7");
                        break;
                }
            }
            else
                System.out.println("Error: Please enter valid number");
        }
    }
}

