package com.jokes;

import java.util.Random;

public class JokesManager {

    String[] listOfJokes ;
    public JokesManager(){

         listOfJokes = new String[]{
                "Why do programmers always mix up Halloween and Christmas? Because Oct 31 equals Dec 25!"
                ," Knock, knock,Who there? very long pause… Java."
                ,"how many programmers does it take to change a light bulb?none, that's a hardware problem "
                ,"When your hammer is C++, everything begins to look like a thumb"
                ,"If you put a million monkeys at a million keyboards, one of them will eventually write a Java program ,The rest of them will write Perl programs"
                ,"Whats the object-oriented way to become wealthy? Inheritance"
                ,"To understand what recursion is, you must first understand recursion"
                ,"There are 2 types of people in the world. Those who understand binary and those who doesnt "
                ,"Why programmers like UNIX unzip, strip, touch, finger, grep, mount, fsck, more, yes, fsck, fsck, fsck, umount, sleep"

        };

    }

    public String getAJokes(){

        Random rand = new Random();



        long  range  =  listOfJokes.length - 1;
        long fraction = (long)(range * rand.nextDouble());

        int randNumber =  (int) fraction + 1;


        return  listOfJokes[randNumber];
    }
}
