package com.example.gameLogic;

import java.util.*;

public class Hand {
    private String hand = "";
    private String displayHand = "";
    public Hand() {
    }

    public Hand(String hand) {
        this.hand = hand;
    }

    public String getHand() {
        return displayHand;
    }

    public void resetHand() {
        displayHand = "";
    }

    public String generateDiceNumbers(List<Integer> indexes) {
        System.out.println("Generating new dice: ");
        for (Integer i : indexes) {
            generateDiceNumber(i);
            System.out.println("Index: " + i + " and value: " + displayHand.toCharArray()[i]);
        }
        return displayHand;
    }

    public void generateDiceNumber(int location) {
        if (location < displayHand.length()) {
            StringBuilder b = new StringBuilder(displayHand);
            b.setCharAt(location, Character.forDigit((int) ((Math.random() * (7 - 1)) + 1), 10));
            displayHand = b.toString();
        } else {
            displayHand += (int) ((Math.random() * (7 - 1)) + 1);
        }
        hand = displayHand;
    }

    public List<Integer> getAllScores() {
        char[] temp = hand.toCharArray();
        Arrays.sort(temp);
        hand = new String(temp);
        //System.out.println(hand);
//        System.out.println(displayHand);
        List<Integer> scores = new ArrayList<>();
        scores.add(getOnes());
        scores.add(getTwos());
        scores.add(getThrees());
        scores.add(getFours());
        scores.add(getFives());
        scores.add(getSixes());

        scores.add(getThreeOfAKind());
        scores.add(getFourOfAKind());
        scores.add(getFullHouse());
        scores.add(getSmallStraight()); //Get unique numbers 23345 not working
        scores.add(getLargeStraight());
        scores.add(getYatzy());
        scores.add(getChance());
        return scores;
    }

    public int getOnes() {
        //Ones: The sum of all dice showing the number 1.
        return (int) hand.chars().filter(c -> c == '1').count();
    }

    public int getTwos() {
        //Twos: The sum of all dice showing the number 2.
        return (int) hand.chars().filter(c -> c == '2').count() * 2;
    }

    public int getThrees() {
        //Threes: The sum of all dice showing the number 3.
        return (int) hand.chars().filter(c -> c == '3').count() * 3;
    }

    public int getFours() {
        //Fours: The sum of all dice showing the number 4.
        return (int) hand.chars().filter(c -> c == '4').count() * 4;
    }

    public int getFives() {
        //Fives: The sum of all dice showing the number 5.
        return (int) hand.chars().filter(c -> c == '5').count() * 5;
    }

    public int getSixes() {
        //Sixes: The sum of all dice showing the number 6.
        return (int) hand.chars().filter(c -> c == '6').count() * 6;
    }

    public int getThreeOfAKind() {
        //Three of a Kind: Three dice showing the same number. Score: Sum of those three dice.
        //***??
        //?***?
        //??***
        if (matcher(new ArrayList<>(List.of("***??", "?***?", "??***"))))
            return hand.chars().map(c -> Character.getNumericValue(c)).sum();
        return 0;
    }

    public int getFourOfAKind() {
        //Four of a Kind: Four dice with the same number. Score: Sum of those four dice.
        //****?
        //?****
        if (matcher(new ArrayList<>(List.of("****?", "?****"))))
            return hand.chars().map(c -> Character.getNumericValue(c)).sum();
        return 0;
    }

    public int getSmallStraight() {
        //Small Straight: The combination 1-2-3-4-5. Score: 15 points (sum of all the dice).
        //30 points
        //1234?
        //?2345
        //?3456
        if (matcher(new ArrayList<>(List.of("1234?", "?1234", "1?234", "123?4", "2345?", "?2345", "23?45", "234?5", "3456?", "?3456", "34?56", "345?6"))))
            return 30;
        return 0;
    }

    public int getLargeStraight() {
        //Large Straight: The combination 2-3-4-5-6. Score: 20 points (sum of all the dice).
        //12345
        //23456
        //40 points
        if (matcher(new ArrayList<>(List.of("12345", "23456"))))
            return 40;
        return 0;
    }

    public int getFullHouse() {
        //Full House: Any set of three combined with a different pair. Score: Sum of all the dice.
        //**??? and ??***
        //***?? and ???**
        if (matcher(new ArrayList<>(List.of("**???"))) && matcher(new ArrayList<>(List.of("??***"))) ||
                matcher(new ArrayList<>(List.of("***??"))) && matcher(new ArrayList<>(List.of("???**"))))
            return 25;
        return 0;
    }

    public int getChance() {
        //Chance: Any combination of dice. Score: Sum of all the dice.
        return hand.chars().map(c -> Character.getNumericValue(c)).sum();
    }

    public int getYatzy() {
        //Yatzy: All five dice with the same number. Score: 50 points.
        int count = (int) hand.chars().filter(c -> c == hand.charAt(0)).count();
        if (count == 5)
            return 50;
        return 0;
    }

    private boolean matcher(List<String> patterns) {
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
