package com.github.shaneschuller;

/**
 * Created by Shane on 7/24/2015.
 */
public enum Coin {
        PENNY(0.01),
        NICKEL(0.05),
        DIME(0.1),
        QUARTER(0.25),
        HALF_DOLLAR(0.50),
        DOLLAR(1.0);
        Coin(double value) { this.value = value; }
        private final double value;
        public double value() { return value; }
}
