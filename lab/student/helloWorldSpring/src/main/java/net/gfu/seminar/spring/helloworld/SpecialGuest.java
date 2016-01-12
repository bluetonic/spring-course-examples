package net.gfu.seminar.spring.helloworld;

import java.util.Scanner;

/**
 * Created by user1 on 12.01.2016.
 */
public class SpecialGuest extends Guest {

    public SpecialGuest(String firstName, String lastName) {
        super(firstName,lastName);
    }

    @Override
    public String getName() {
        return "special guest " + super.getName();
    }

    @Override
    public String toString() {
        return "SpecialGuest{ "+ getFirstName() +
                " " + getLastName()+" }";
    }
}
