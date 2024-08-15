package com.example.gameLogic;

import java.util.*;

public class HandLogic {
    public static String generateDiceNumbers(List<Integer> indexes, String hand) {
        if (hand.isEmpty()) {
            hand = "11111";
        }
        StringBuilder sb = new StringBuilder(hand);
        for (Integer i : indexes) {
            sb.setCharAt(i, Character.forDigit((int) ((Math.random() * (7 - 1)) + 1), 10));
        }
        return sb.toString();
    }

    public static List<Integer> getAllScores(String hand) {
        char[] temp = hand.toCharArray();
        Arrays.sort(temp);
        hand = new String(temp);
        //System.out.println(hand);
//        System.out.println(displayHand);
        List<Integer> scores = new ArrayList<>();
        scores.add(getOnes(hand));
        scores.add(getTwos(hand));
        scores.add(getThrees(hand));
        scores.add(getFours(hand));
        scores.add(getFives(hand));
        scores.add(getSixes(hand));

        scores.add(getThreeOfAKind(hand));
        scores.add(getFourOfAKind(hand));
        scores.add(getFullHouse(hand));
        scores.add(getSmallStraight(hand)); //Get unique numbers 23345 not working
        scores.add(getLargeStraight(hand));
        scores.add(getYatzy(hand));
        scores.add(getChance(hand));
        return scores;
    }

    public static int getOnes(String hand) {
        //Ones: The sum of all dice showing the number 1.
        return (int) hand.chars().filter(c -> c == '1').count();
    }

    public static int getTwos(String hand) {
        //Twos: The sum of all dice showing the number 2.
        return (int) hand.chars().filter(c -> c == '2').count() * 2;
    }

    public static int getThrees(String hand) {
        //Threes: The sum of all dice showing the number 3.
        return (int) hand.chars().filter(c -> c == '3').count() * 3;
    }

    public static int getFours(String hand) {
        //Fours: The sum of all dice showing the number 4.
        return (int) hand.chars().filter(c -> c == '4').count() * 4;
    }

    public static int getFives(String hand) {
        //Fives: The sum of all dice showing the number 5.
        return (int) hand.chars().filter(c -> c == '5').count() * 5;
    }

    public static int getSixes(String hand) {
        //Sixes: The sum of all dice showing the number 6.
        return (int) hand.chars().filter(c -> c == '6').count() * 6;
    }

    public static int getThreeOfAKind(String hand) {
        //Three of a Kind: Three dice showing the same number. Score: Sum of those three dice.
        //***??
        //?***?
        //??***
        if (matcher(new ArrayList<>(List.of("***??", "?***?", "??***")), hand))
            return hand.chars().map(c -> Character.getNumericValue(c)).sum();
        return 0;
    }

    public static int getFourOfAKind(String hand) {
        //Four of a Kind: Four dice with the same number. Score: Sum of those four dice.
        //****?
        //?****
        if (matcher(new ArrayList<>(List.of("****?", "?****")), hand))
            return hand.chars().map(c -> Character.getNumericValue(c)).sum();
        return 0;
    }

    public static int getSmallStraight(String hand) {
        //Small Straight: The combination 1-2-3-4-5. Score: 15 points (sum of all the dice).
        //30 points
        //1234?
        //?2345
        //?3456
        if (matcher(new ArrayList<>(List.of("1234?", "?1234", "1?234", "123?4", "2345?", "?2345", "23?45", "234?5", "3456?", "?3456", "34?56", "345?6")), hand))
            return 30;
        return 0;
    }

    public static int getLargeStraight(String hand) {
        //Large Straight: The combination 2-3-4-5-6. Score: 20 points (sum of all the dice).
        //12345
        //23456
        //40 points
        if (matcher(new ArrayList<>(List.of("12345", "23456")), hand))
            return 40;
        return 0;
    }

    public static int getFullHouse(String hand) {
        //Full House: Any set of three combined with a different pair. Score: Sum of all the dice.
        //**??? and ??***
        //***?? and ???**
        if (getYatzy(hand) == 0) {
            if (matcher(new ArrayList<>(List.of("**???")), hand) && matcher(new ArrayList<>(List.of("??***")), hand) ||
                    matcher(new ArrayList<>(List.of("***??")), hand) && matcher(new ArrayList<>(List.of("???**")), hand))
                return 25;
        }
        return 0;
    }

    public static int getChance(String hand) {
        //Chance: Any combination of dice. Score: Sum of all the dice.
        return hand.chars().map(c -> Character.getNumericValue(c)).sum();
    }

    public static int getYatzy(String hand) {
        //Yatzy: All five dice with the same number. Score: 50 points.
        int count = (int) hand.chars().filter(c -> c == hand.charAt(0)).count();
        if (count == 5)
            return 50;
        return 0;
    }

    private static boolean matcher(List<String> patterns, String hand) {
        //***??
        //?***?
        //??***
        //System.out.println("Hand is " + hand);
        boolean patternFound = false;
        char x = '0';
        for (String pattern: patterns) {
            //System.out.println(pattern);
            x = '0';
            patternFound = true;
            for (int i = 0; i < pattern.length(); i++) {
                if (pattern.charAt(i) == '?') {
                    //System.out.println("? Question mark");
                    continue;
                }
                if (pattern.charAt(i) == '*') {
                    //System.out.println("* Star at index " + i);
                    if (x == '0') {
                        //System.out.println("Star was nil at index " + i);
                        x = hand.charAt(i);
                    } else if (x != hand.charAt(i)) {
                        //System.out.println(x + " is not equal to " + hand.charAt(i) + " at index " + i);
                        patternFound = false;
                        break;
                    }
                } else if (pattern.charAt(i) != hand.charAt(i)) {
                    //System.out.println("Ordinary number not matching");
                    patternFound = false;
                    break;
                }
            }
            //System.out.println("\n--------------------------\n");
            if (patternFound)
                break;
        }
        return patternFound;
    }
}
