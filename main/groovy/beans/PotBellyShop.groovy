/* ===================================================
 * Copyright 2012 AEGIS.net, Inc.
 * 
 * Description:  
 * This Groovy class is a good example of a Java bean.
 * Notice that there are no semi-colons, no getters-
 * setters (which are automatically provided by Groovy
 * with a public access), and the variables are private
 * by default.  You may still use Java's access
 * modifiers as per your code demands.
 * =================================================== */
package beans;

class PotBellyShop {
    int id
    String street
    String city
    String state
    int visitors
    double latitude
    double longitude

    String toString() { "($street,$city,$state,$visitors,$latitude,$longitude)" }
}
