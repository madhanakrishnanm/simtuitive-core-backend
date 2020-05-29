package com.simtuitive.core.util;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordHelper {        

    public static String generatePassword (int length) {    

    final char[] lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    final char[] uppercase = "ABCDEFGJKLMNPRSTUVWXYZ".toCharArray();
    final char[] numbers = "0123456789".toCharArray();
    final char[] symbols = "^$?!@#%&".toCharArray();
    final char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789^$?!@#%&".toCharArray();

    //Use cryptographically secure random number generator
    Random random = new SecureRandom();

    StringBuilder password = new StringBuilder(); 

    for (int i = 0; i < length-4; i++) {
        password.append(allAllowed[random.nextInt(allAllowed.length)]);
    }

    //Ensure password policy is met by inserting required random chars in random positions
    password.insert(random.nextInt(password.length()), lowercase[random.nextInt(lowercase.length)]);
    password.insert(random.nextInt(password.length()), uppercase[random.nextInt(uppercase.length)]);
    password.insert(random.nextInt(password.length()), numbers[random.nextInt(numbers.length)]);
    password.insert(random.nextInt(password.length()), symbols[random.nextInt(symbols.length)]);
    
System.out.println("password"+password.toString());
    return password.toString();

    }
    
    public static String generateEventPassword (int length) {    

        
        final char[] uppercase = "ABCDEFGJKLMNOPQRSTUVWXYZ".toCharArray();
        final char[] numbers = "0123456789".toCharArray();        
        final char[] allAllowed = "ABCDEFGJKLMNPRSTUVWXYZ0123456789".toCharArray();

        //Use cryptographically secure random number generator
        Random random = new SecureRandom();

        StringBuilder password = new StringBuilder(); 

        for (int i = 0; i < length-2; i++) {
            password.append(allAllowed[random.nextInt(allAllowed.length)]);
        }

        //Ensure password policy is met by inserting required random chars in random positions
       
        password.insert(random.nextInt(password.length()), uppercase[random.nextInt(uppercase.length)]);
        password.insert(random.nextInt(password.length()), numbers[random.nextInt(numbers.length)]);
        
        
    System.out.println("password"+password.toString());
        return password.toString();

        }


}

