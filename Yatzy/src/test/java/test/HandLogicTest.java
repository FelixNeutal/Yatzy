package test;

import com.example.gameLogic.HandLogic;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HandLogicTest {
    @Test
    public void canGetOnes() {
        //assertThat(read.getOrderNumber(), is("A789"));
        HandLogic hand = new HandLogic("13345");
        assertThat(hand.getOnes(), is(1));
        hand = new HandLogic("13145");
        assertThat(hand.getOnes(), is(2));
        hand = new HandLogic("11315");
        assertThat(hand.getOnes(), is(3));
        hand = new HandLogic("11311");
        assertThat(hand.getOnes(), is(4));
        hand = new HandLogic("11111");
        assertThat(hand.getOnes(), is(5));
        hand = new HandLogic("24463");
        assertThat(hand.getOnes(), is(0));
    }

    @Test
    public void canGetTwos() {
        //assertThat(read.getOrderNumber(), is("A789"));
        HandLogic hand = new HandLogic("24463");
        assertThat(hand.getTwos(), is(2));
        hand = new HandLogic("24462");
        assertThat(hand.getTwos(), is(4));
        hand = new HandLogic("22462");
        assertThat(hand.getTwos(), is(6));
        hand = new HandLogic("24222");
        assertThat(hand.getTwos(), is(8));
        hand = new HandLogic("22222");
        assertThat(hand.getTwos(), is(10));
        hand = new HandLogic("14463");
        assertThat(hand.getTwos(), is(0));
    }

    @Test
    public void canGetThrees() {
        //assertThat(read.getOrderNumber(), is("A789"));
        HandLogic hand = new HandLogic("51634");
        assertThat(hand.getThrees(), is(3));
        hand = new HandLogic("53634");
        assertThat(hand.getThrees(), is(6));
        hand = new HandLogic("33634");
        assertThat(hand.getThrees(), is(9));
        hand = new HandLogic("33633");
        assertThat(hand.getThrees(), is(12));
        hand = new HandLogic("33333");
        assertThat(hand.getThrees(), is(15));
        hand = new HandLogic("51624");
        assertThat(hand.getThrees(), is(0));
    }

    @Test
    public void canGetFours() {
        //assertThat(read.getOrderNumber(), is("A789"));
        HandLogic hand = new HandLogic("66241");
        assertThat(hand.getFours(), is(4));
        hand = new HandLogic("64241");
        assertThat(hand.getFours(), is(8));
        hand = new HandLogic("66444");
        assertThat(hand.getFours(), is(12));
        hand = new HandLogic("64444");
        assertThat(hand.getFours(), is(16));
        hand = new HandLogic("44444");
        assertThat(hand.getFours(), is(20));
        hand = new HandLogic("66251");
        assertThat(hand.getFours(), is(0));
    }

    @Test
    public void canGetFives() {
        //assertThat(read.getOrderNumber(), is("A789"));
        HandLogic hand = new HandLogic("51216");
        assertThat(hand.getFives(), is(5));
        hand = new HandLogic("51215");
        assertThat(hand.getFives(), is(10));
        hand = new HandLogic("55516");
        assertThat(hand.getFives(), is(15));
        hand = new HandLogic("55255");
        assertThat(hand.getFives(), is(20));
        hand = new HandLogic("55555");
        assertThat(hand.getFives(), is(25));
        hand = new HandLogic("11216");
        assertThat(hand.getFives(), is(0));
    }

    @Test
    public void canGetSixes() {
        //assertThat(read.getOrderNumber(), is("A789"));
        HandLogic hand = new HandLogic("62341");
        assertThat(hand.getSixes(), is(6));
        hand = new HandLogic("62361");
        assertThat(hand.getSixes(), is(12));
        hand = new HandLogic("62366");
        assertThat(hand.getSixes(), is(18));
        hand = new HandLogic("66661");
        assertThat(hand.getSixes(), is(24));
        hand = new HandLogic("66666");
        assertThat(hand.getSixes(), is(30));
        hand = new HandLogic("02351");
        assertThat(hand.getSixes(), is(0));
    }

    @Test
    public void canGetThreeOfAKind() {
        HandLogic hand = new HandLogic("22333");
        assertThat(hand.getThreeOfAKind(), is(13));
        hand = new HandLogic("22533");
        assertThat(hand.getThreeOfAKind(), is(0));
        hand = new HandLogic("22233");
        assertThat(hand.getThreeOfAKind(), is(12));
        hand = new HandLogic("12223");
        assertThat(hand.getThreeOfAKind(), is(10));
    }

    @Test
    public void canGetFourOfAKind() {
        HandLogic hand = new HandLogic("44442");
        assertThat(hand.getFourOfAKind(), is(18));
        hand = new HandLogic("24444");
        assertThat(hand.getFourOfAKind(), is(18));
        hand = new HandLogic("53333");
        assertThat(hand.getFourOfAKind(), is(17));
        hand = new HandLogic("22333");
        assertThat(hand.getFourOfAKind(), is(0));
        hand = new HandLogic("23332");
        assertThat(hand.getFourOfAKind(), is(0));
        hand = new HandLogic("56665");
        assertThat(hand.getFourOfAKind(), is(0));
        hand = new HandLogic("26666");
        assertThat(hand.getFourOfAKind(), is(26));
    }

    @Test
    public void canGetSmallStraight() {
        //"1234?", "?1234", "2345?", "?2345", "3456?", "?3456"
        HandLogic hand = new HandLogic("12345");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new HandLogic("21234");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new HandLogic("23456");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new HandLogic("32345");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new HandLogic("34562");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new HandLogic("13456");
        assertThat(hand.getSmallStraight(), is(30));
    }

    @Test
    public void canGetLargeStraight() {
        HandLogic hand = new HandLogic("12345");
        assertThat(hand.getLargeStraight(), is(40));
        hand = new HandLogic("23456");
        assertThat(hand.getLargeStraight(), is(40));
        hand = new HandLogic("12344");
        assertThat(hand.getLargeStraight(), is(0));
        hand = new HandLogic("23345");
        assertThat(hand.getLargeStraight(), is(0));
    }

    @Test
    public void canGetFullHouse() {
        HandLogic hand = new HandLogic("22333");
        assertThat(hand.getFullHouse(), is(25));
        hand = new HandLogic("22233");
        assertThat(hand.getFullHouse(), is(25));
        hand = new HandLogic("22353");
        assertThat(hand.getFullHouse(), is(0));
    }

    @Test
    public void canGetChance() {
        HandLogic hand = new HandLogic("12345");
        assertThat(hand.getChance(), is(15));
        hand = new HandLogic("66666");
        assertThat(hand.getChance(), is(30));
    }

    @Test
    public void canGetYatzy() {
        HandLogic hand = new HandLogic("66666");
        assertThat(hand.getYatzy(), is(50));
        hand = new HandLogic("33333");
        assertThat(hand.getYatzy(), is(50));
        hand = new HandLogic("26666");
        assertThat(hand.getYatzy(), is(0));
    }
}
