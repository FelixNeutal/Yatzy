package com.example.game;

import com.example.gameLogic.HandLogic;

import java.util.*;
import java.util.stream.Collectors;

public class BotGame22 extends Game {
    Scores scores = new Scores();
    private int totalScore = 0;
    private List<GameMove> moves = new ArrayList<>();
    private int[] upperScores = {-1, -1, -1, -1, -1, -1};
    private int[] lowerScores = {-1, -1, -1, -1, -1, -1, -1};
    private int randomIndex = 12;
    public BotGame22() {
        super();
    }

    public GameMove getPlayerMove() {
        //GameMove(String dices, int score, ScoreType scoreType)
        try {
            Thread.sleep((long) (Math.random() * 2000) + 1000);
        } catch (InterruptedException ignored) {}
        //Hand hand = new Hand("23455");
        String hand = "";
        hand = HandLogic.generateDiceNumbers(Arrays.asList(1, 2, 3, 4, 5), hand);
        if (scores.largeStraight == -1) {
            if (HandLogic.getLargeStraight(hand) > 0) {
                System.out.println("Was large straight");
                scores.largeStraight = HandLogic.getLargeStraight(hand);
                lowerScores[4] = HandLogic.getLargeStraight(hand);
                //Stop, hammer time. Actually players turn now
                return new GameMove();
            } else if (HandLogic.getSmallStraight(hand) > 0) {
                System.out.println("Was small straight. Trying to get large");
                //int index = HandLogic.getSmallStraightThrowableIndex();
                for (int i = 0; i < 2; i++) {
                  //  hand = HandLogic.generateDiceNumber(index);
                    System.out.println("New hand " + hand);
                    if (HandLogic.getLargeStraight(hand) > 0) {
                        System.out.println("Got large straight");
                        scores.largeStraight = HandLogic.getLargeStraight(hand);
                        lowerScores[4] = HandLogic.getLargeStraight(hand);
                        //Stop
                        return new GameMove();
                    }
                }
            }
        }
        if (scores.smallStraight == -1) {
            System.out.println("Small straight is empty");
            if (HandLogic.getSmallStraight(hand) > 0) {
                System.out.println("Got small straight");
                scores.smallStraight = HandLogic.getSmallStraight(hand);
                lowerScores[3] = HandLogic.getSmallStraight(hand);
                return new GameMove();
            }
        }
        System.out.println("Trying other categories");

        for (int i = 0; i < 2; i++) {
            char[] charArray = hand.toCharArray();
            char maxNumber = getMaxCountDice(charArray);
            if (maxNumber == ' ') {
                hand = HandLogic.generateDiceNumbers(Arrays.asList(0, 1, 2, 3, 4), hand);
            } else {
                List<Integer> indexes = new ArrayList<>();
                for (int j = 0; j < 5; j++) {
                    if (charArray[j] != maxNumber) {
                        indexes.add(j);
                    }
                }
                hand = HandLogic.generateDiceNumbers(indexes, hand);
            }
        }
        //If Yatzee:
        //If sixes and bonus less than 38, put in sixes. Else in Yatzee
        if (HandLogic.getYatzy(hand) > 0) {
            if (getDiceCount(hand, '6') > 0 && currentPlayer.getUpperSectionScore() < 38) {
                System.out.println("Putting to sixes");
                scores.sixes = HandLogic.getSixes(hand);
                upperScores[5] = HandLogic.getSixes(hand);
                return new GameMove();
            } else {
                System.out.println("Putting to Yatzy");
                scores.yahtzee = HandLogic.getYatzy(hand);
                lowerScores[5] = HandLogic.getYatzy(hand);
                return new GameMove();
            }
        }
        //If full house
        if (HandLogic.getFullHouse(hand) > 0) {
            System.out.println("Putting to Full house");
            scores.fullHouse = HandLogic.getFullHouse(hand);
            lowerScores[2] = HandLogic.getFullHouse(hand);
            return new GameMove();
        }
        //If four or three of a kind:
        //If sixes and bonus less than 43, put in sixes, if score is less than 48 put in sixes. Else...
        if (HandLogic.getFourOfAKind(hand) > 0) {
            if (getDiceCount(hand, '6') > 3 && currentPlayer.getUpperSectionScore() < 43 && isScoreCategoryFree('6')) {
                scores.sixes = HandLogic.getSixes(hand);
                upperScores[5] = HandLogic.getSixes(hand);
                System.out.println("Putting to sixes");
                return new GameMove();
            } else {
                scores.fourOfAKind = HandLogic.getFourOfAKind(hand);
                lowerScores[1] = HandLogic.getFourOfAKind(hand);
                System.out.println("Putting to Four of a kind");
                return new GameMove();
            }
        }
        if (HandLogic.getThreeOfAKind(hand) > 0) {
            if (getDiceCount(hand, '6') > 2 && currentPlayer.getUpperSectionScore() < 48 && isScoreCategoryFree('6')) {
                scores.sixes = HandLogic.getSixes(hand);
                upperScores[5] = HandLogic.getSixes(hand);
                System.out.println("Putting to Sixes");
                return new GameMove();
            } else {
                System.out.println("Putting to Three of a kind");
                scores.threeOfAKind = HandLogic.getThreeOfAKind(hand);
                lowerScores[0] = HandLogic.getThreeOfAKind(hand);
                return new GameMove();
            }
        }
        //Something
        //Chance if 10 or greater
        if (HandLogic.getChance(hand) >= 10) {
            scores.choice = HandLogic.getChance(hand);
            System.out.println("Putting to Chance");
            return new GameMove();
        }
        //Otherwise 0 lower to upper ascending order (top to bottom)
        for (int i = 0; i < lowerScores.length; i++) {
            if (lowerScores[i] == -1) {
                lowerScores[i] = 0;
                return new GameMove();
            }
        }
        for (int i = 0; i < upperScores.length; i++) {
            if (upperScores[i] == -1) {
                upperScores[i] = 0;
                return new GameMove();
            }
        }
        return new GameMove();
    }

    private int getDiceCount(String h, char c) {
        int count = 0;
        for (Character ch : h.toCharArray()) {
            if (ch == c) {
                count++;
            }
        }
        return count;
    }

    private char getMaxCountDice(char[] charArray) {
        char maxChar = ' ';
        Map<Character, Integer> maxNumbers = new HashMap<>();
        for (Character c : charArray) {
            maxNumbers.put(c, maxNumbers.getOrDefault(c, 0) + 1);
        }
        List<Character> sortedMap = maxNumbers.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for (int i = sortedMap.size() - 1; i >= 0; i--) {
            if (isScoreCategoryFree(sortedMap.get(i))) {
                maxChar = sortedMap.get(i);
                break;
            }
        }
        return maxChar;
    }

    private boolean isScoreCategoryFree(char character) {
        return true;
    }

    private class Scores {
        public int ones = -1;
        public int twos = -1;
        public int threes = -1;
        public int fours = -1;
        public int fives = -1;
        public int sixes = -1;
        public int threeOfAKind = -1;
        public int fourOfAKind = -1;
        public int fullHouse = -1;
        public int smallStraight = -1;
        public int largeStraight = -1;
        public int yahtzee = -1;
        public int choice = -1;
    }
}
