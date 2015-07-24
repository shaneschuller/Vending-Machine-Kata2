package com.github.shaneschuller;

/**
 * Created by Shane on 7/24/2015.
 */
public enum Selection
{
    COLA(1.00),
    CHIPS(0.50),
    CANDY(0.65);
    Selection(double value) { this.value = value; }
    private final double value;
    public double value() { return value; }
}