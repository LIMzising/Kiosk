package com.mypackage;

import java.util.Scanner;

public class Customer
{
    private String name;
    private String totalItems;
    private int totalCosts;
    private int creditBalance;

    public Customer()
    {
        name = "";
        totalItems = "";
        totalCosts = 0;
        creditBalance = 0;
    }

    public Customer(String yourName)
    {
        name = yourName;
        totalItems = "";
        totalCosts = 0;
        creditBalance = 0;
    }

    public void display()
    {
        if (name.length() == 0)
            System.out.println("Name: Not defined");
        else
            System.out.println("Name: " + name);
        if (totalItems.length() == 0)
            System.out.println("Total items purchased: None");
        else
            System.out.println("Total items purchased: " + totalItems);
        System.out.println("Total costs purchased: " + totalCosts);
        System.out.println("Credit Balance: " + creditBalance);
    }

    public int getCreditBalance()
    {
        return creditBalance;
    }

    public String getName()
    {
        return name;
    }

    public int getTotalCosts()
    {
        return totalCosts;
    }

    public String getTotalItems()
    {
        return totalItems;
    }

    public void setCreditBalance(int creditPurchased, int itemCost)
    {
        creditBalance = creditBalance + creditPurchased - itemCost;
    }

    public void setName(String yourName)
    {
        name = yourName;
    }

    public void setTotalCosts(int itemCost)
    {
        totalCosts = totalCosts + itemCost;
    }

    public void setTotalItems(String itemName)
    {
        if (totalItems.length() > 0)
            totalItems = totalItems + ", ";
        totalItems = totalItems + itemName;
    }
}

