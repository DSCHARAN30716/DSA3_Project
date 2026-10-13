package service;

import algorithms.Levenshtein;

import java.util.Arrays;
import java.util.List;

public class AddressCorrection {

    private final List<String> knownLocations =
            Arrays.asList(
                    "Hyderabad",
                    "Vijayawada",
                    "Visakhapatnam",
                    "Chennai",
                    "Bangalore",
                    "Mumbai",
                    "Delhi",
                    "Kolkata",
                    "Pune",
                    "Bhimavaram"
            );

    public String correctAddress(String input) {

        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        input = input.trim();

        String bestMatch = null;
        int minimumDistance = Integer.MAX_VALUE;

        for (String location : knownLocations) {

            int distance =
                    Levenshtein.distance(
                            input.toLowerCase(),
                            location.toLowerCase()
                    );

            if (distance < minimumDistance) {

                minimumDistance = distance;
                bestMatch = location;
            }
        }

        /*
         * Do not suggest a completely unrelated city.
         * A maximum edit distance of 4 is used.
         */

        if (minimumDistance > 4) {
            return input;
        }

        return bestMatch;
    }

    public int getDistance(String input, String corrected) {

        return Levenshtein.distance(
                input.toLowerCase(),
                corrected.toLowerCase()
        );
    }

    public void displayCorrection(String input) {

        String corrected = correctAddress(input);

        int distance =
                getDistance(input, corrected);

        System.out.println();
        System.out.println("========== ADDRESS CORRECTION ==========");

        System.out.println("Entered Address  : " + input);
        System.out.println("Suggested Address: " + corrected);
        System.out.println("Edit Distance    : " + distance);

        if (input.equalsIgnoreCase(corrected)) {

            System.out.println(
                    "Status            : Address is correct"
            );

        } else {

            System.out.println(
                    "Status            : Correction suggested"
            );
        }
    }
}