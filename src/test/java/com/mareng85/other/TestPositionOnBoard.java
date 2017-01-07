package com.mareng85.other;

import com.mareng85.BaseTest;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPositionOnBoard extends BaseTest {

    @Test
    public void testRegexp() {
        String s = "A1";
        Pattern pattern = Pattern.compile("[a-hA-H1-8]{2}");
        Matcher matcher = pattern.matcher(s);
        boolean found = matcher.find();
        if (found) {
            System.out.println(matcher.group()+" is valid!");
        }
    }

    @Test
    public void testGetPositionOnBoard() {
        int[] pos = board.getPositionOnBoard("A1");
        System.out.println("Pos[0] = " + pos[0]+", Pos[1] = " + pos[1]);

        pos = board.getPositionOnBoard("B1");
        System.out.println("Pos[0] = " + pos[0]+", Pos[1] = " + pos[1]);

        pos = board.getPositionOnBoard("C1");
        System.out.println("Pos[0] = " + pos[0]+", Pos[1] = " + pos[1]);

        pos = board.getPositionOnBoard("D1");
        System.out.println("Pos[0] = " + pos[0]+", Pos[1] = " + pos[1]);

        pos = board.getPositionOnBoard("E1");
        System.out.println("Pos[0] = " + pos[0]+", Pos[1] = " + pos[1]);

        pos = board.getPositionOnBoard("F1");
        System.out.println("Pos[0] = " + pos[0]+", Pos[1] = " + pos[1]);

        pos = board.getPositionOnBoard("G1");
        System.out.println("Pos[0] = " + pos[0]+", Pos[1] = " + pos[1]);

        pos = board.getPositionOnBoard("H1");
        System.out.println("Pos[0] = " + pos[0]+", Pos[1] = " + pos[1]);

        pos = board.getPositionOnBoard("B7");
        System.out.println("Pos[0] = " + pos[0]+", Pos[1] = " + pos[1]);
    }

    @Test(expected=RuntimeException.class)
    public void testGetPositionOnBoardError() {
        int[] pos = board.getPositionOnBoard("A9");
    }
}
