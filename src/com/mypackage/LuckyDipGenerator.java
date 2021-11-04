package com.mypackage;

public class LuckyDipGenerator
{
    private int maximumNumber;

    public LuckyDipGenerator(int UpperLimit)
    {
        maximumNumber = UpperLimit;
    }

    public int randomNumber()
    {
        int randomNumber = 1 + (int)(Math.random() * maximumNumber);
        return randomNumber;
    }
}

