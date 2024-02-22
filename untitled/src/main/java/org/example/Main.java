package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Map<String, Map<String, Integer>> storedValues = new LinkedHashMap<>();

        while (!input.equals("no more time")) {
            String[] inputArray = input.split(" -> ");
            String username = inputArray[0];
            String contest = inputArray[1];
            int points = Integer.parseInt(inputArray[2]);

            if (!storedValues.containsKey(contest)) {
                storedValues.put(contest, new LinkedHashMap<>());
                storedValues.get(contest).put(username, points);
            }
            else if (storedValues.get(contest).containsKey(username)) {
                if (points > storedValues.get(contest).get(username)) {
                    storedValues.get(contest).put(username, points);
                }

            }
            else {
                storedValues.get(contest).put(username, points);
            }
        }

    }
}