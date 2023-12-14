package test;

import com.example.gameLogic.Hand;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HandTest {
    @Test
    public void canGetOnes() {
        //assertThat(read.getOrderNumber(), is("A789"));
        Hand hand = new Hand("13345");
        assertThat(hand.getOnes(), is(1));
        hand = new Hand("13145");
        assertThat(hand.getOnes(), is(2));
        hand = new Hand("11315");
        assertThat(hand.getOnes(), is(3));
        hand = new Hand("11311");
        assertThat(hand.getOnes(), is(4));
        hand = new Hand("11111");
        assertThat(hand.getOnes(), is(5));
        hand = new Hand("24463");
        assertThat(hand.getOnes(), is(0));
    }

    @Test
    public void canGetTwos() {
        //assertThat(read.getOrderNumber(), is("A789"));
        Hand hand = new Hand("24463");
        assertThat(hand.getTwos(), is(2));
        hand = new Hand("24462");
        assertThat(hand.getTwos(), is(4));
        hand = new Hand("22462");
        assertThat(hand.getTwos(), is(6));
        hand = new Hand("24222");
        assertThat(hand.getTwos(), is(8));
        hand = new Hand("22222");
        assertThat(hand.getTwos(), is(10));
        hand = new Hand("14463");
        assertThat(hand.getTwos(), is(0));
    }

    @Test
    public void canGetThrees() {
        //assertThat(read.getOrderNumber(), is("A789"));
        Hand hand = new Hand("51634");
        assertThat(hand.getThrees(), is(3));
        hand = new Hand("53634");
        assertThat(hand.getThrees(), is(6));
        hand = new Hand("33634");
        assertThat(hand.getThrees(), is(9));
        hand = new Hand("33633");
        assertThat(hand.getThrees(), is(12));
        hand = new Hand("33333");
        assertThat(hand.getThrees(), is(15));
        hand = new Hand("51624");
        assertThat(hand.getThrees(), is(0));
    }

    @Test
    public void canGetFours() {
        //assertThat(read.getOrderNumber(), is("A789"));
        Hand hand = new Hand("66241");
        assertThat(hand.getFours(), is(4));
        hand = new Hand("64241");
        assertThat(hand.getFours(), is(8));
        hand = new Hand("66444");
        assertThat(hand.getFours(), is(12));
        hand = new Hand("64444");
        assertThat(hand.getFours(), is(16));
        hand = new Hand("44444");
        assertThat(hand.getFours(), is(20));
        hand = new Hand("66251");
        assertThat(hand.getFours(), is(0));
    }

    @Test
    public void canGetFives() {
        //assertThat(read.getOrderNumber(), is("A789"));
        Hand hand = new Hand("51216");
        assertThat(hand.getFives(), is(5));
        hand = new Hand("51215");
        assertThat(hand.getFives(), is(10));
        hand = new Hand("55516");
        assertThat(hand.getFives(), is(15));
        hand = new Hand("55255");
        assertThat(hand.getFives(), is(20));
        hand = new Hand("55555");
        assertThat(hand.getFives(), is(25));
        hand = new Hand("11216");
        assertThat(hand.getFives(), is(0));
    }

    @Test
    public void canGetSixes() {
        //assertThat(read.getOrderNumber(), is("A789"));
        Hand hand = new Hand("62341");
        assertThat(hand.getSixes(), is(6));
        hand = new Hand("62361");
        assertThat(hand.getSixes(), is(12));
        hand = new Hand("62366");
        assertThat(hand.getSixes(), is(18));
        hand = new Hand("66661");
        assertThat(hand.getSixes(), is(24));
        hand = new Hand("66666");
        assertThat(hand.getSixes(), is(30));
        hand = new Hand("02351");
        assertThat(hand.getSixes(), is(0));
    }

    @Test
    public void canGetThreeOfAKind() {
        Hand hand = new Hand("22333");
        assertThat(hand.getThreeOfAKind(), is(13));
        hand = new Hand("22533");
        assertThat(hand.getThreeOfAKind(), is(0));
        hand = new Hand("22233");
        assertThat(hand.getThreeOfAKind(), is(12));
        hand = new Hand("12223");
        assertThat(hand.getThreeOfAKind(), is(10));
    }

    @Test
    public void canGetFourOfAKind() {
        Hand hand = new Hand("44442");
        assertThat(hand.getFourOfAKind(), is(18));
        hand = new Hand("24444");
        assertThat(hand.getFourOfAKind(), is(18));
        hand = new Hand("53333");
        assertThat(hand.getFourOfAKind(), is(17));
        hand = new Hand("22333");
        assertThat(hand.getFourOfAKind(), is(0));
        hand = new Hand("23332");
        assertThat(hand.getFourOfAKind(), is(0));
        hand = new Hand("56665");
        assertThat(hand.getFourOfAKind(), is(0));
        hand = new Hand("26666");
        assertThat(hand.getFourOfAKind(), is(26));
    }

    @Test
    public void canGetSmallStraight() {
        //"1234?", "?1234", "2345?", "?2345", "3456?", "?3456"
        Hand hand = new Hand("12345");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new Hand("21234");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new Hand("23456");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new Hand("32345");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new Hand("34562");
        assertThat(hand.getSmallStraight(), is(30));
        hand = new Hand("13456");
        assertThat(hand.getSmallStraight(), is(30));
    }

    @Test
    public void canGetLargeStraight() {
        Hand hand = new Hand("12345");
        assertThat(hand.getLargeStraight(), is(40));
        hand = new Hand("23456");
        assertThat(hand.getLargeStraight(), is(40));
        hand = new Hand("12344");
        assertThat(hand.getLargeStraight(), is(0));
        hand = new Hand("23345");
        assertThat(hand.getLargeStraight(), is(0));
    }

    @Test
    public void canGetFullHouse() {
        Hand hand = new Hand("22333");
        assertThat(hand.getFullHouse(), is(25));
        hand = new Hand("22233");
        assertThat(hand.getFullHouse(), is(25));
        hand = new Hand("22353");
        assertThat(hand.getFullHouse(), is(0));
    }

    @Test
    public void canGetChance() {
        Hand hand = new Hand("12345");
        assertThat(hand.getChance(), is(15));
        hand = new Hand("66666");
        assertThat(hand.getChance(), is(30));
    }

    @Test
    public void canGetYatzy() {
        Hand hand = new Hand("66666");
        assertThat(hand.getYatzy(), is(50));
        hand = new Hand("33333");
        assertThat(hand.getYatzy(), is(50));
        hand = new Hand("26666");
        assertThat(hand.getYatzy(), is(0));
    }
}
