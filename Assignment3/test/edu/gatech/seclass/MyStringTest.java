package edu.gatech.seclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit test class created for use in Georgia Tech CS6300.
 * <p>
 * This class is provided to interpret your grades via junit tests
 * and as a reminder, should NOT be posted in any public repositories,
 * even after the class has ended.
 */

public class MyStringTest {

    private MyStringInterface myString;

    @BeforeEach
    public void setUp() {
        myString = new MyString();
    }

    @AfterEach
    public void tearDown() {
        myString = null;
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: First count number example in the interface documentation
    public void testCountAlphabeticWords1() {
        myString.setString("My numbers are 11, 96, and thirteen");
        assertEquals(5, myString.countAlphabeticWords());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Count alphabetic words in a string that contains consecutive non-alphabetic characters without space characters
    public void testCountAlphabeticWords2() {
        myString.setString("Hello@@@World!##I&&L0ve###^^^^programs.");
        assertEquals(6, myString.countAlphabeticWords());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Count alphabetic words in a string with no alphabetic words
    public void testCountAlphabeticWords3() {
        myString.setString("123!@#$");
        assertEquals(0, myString.countAlphabeticWords());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Throws exception when the string is null
    public void testCountAlphabeticWords4() {
        myString = null;
        assertThrows(NullPointerException.class, ()-> myString.countAlphabeticWords());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Throws exception when the string doesn't contain any letter or number
    public void testSetString1() {
        assertThrows(IllegalArgumentException.class, ()-> myString.setString("#$&^%^()*("));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Throws exception when the string is empty
    public void testSetString2() {
        assertThrows(IllegalArgumentException.class, ()-> myString.setString(""));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Sample encryption 1
    public void testEncrypt1() {
        myString.setString("Cat & 5 DogS");
        assertEquals("1xU & S 65RJ", myString.encrypt(5, 3));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Encrypt a string with a single letter using arg1 = 1 and arg2 = 1
    public void testEncrypt2() {
        myString.setString("A");
        assertEquals("B", myString.encrypt(1, 1));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Encrypt a string with a single number using arg1 = 1 and arg2 = 1
    public void testEncrypt3() {
        myString.setString("5");
        assertEquals("6", myString.encrypt(1, 1));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Encrypt a string with the original string as null
    public void testEncrypt4() {
        myString = null;
        assertThrows(NullPointerException.class, ()-> myString.encrypt(5,3));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Throws IllegalArgumentException when arg2 is not in the valid range
    public void testEncrypt5() {
        myString.setString("This a test for an invalid argument2.");
        assertThrows(IllegalArgumentException.class, ()-> myString.encrypt(5,100));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Throws IllegalArgumentException when arg1 is not co-prime to 62
    public void testEncrypt6() {
        myString.setString("This a test for an invalid argument1.");
        assertThrows(IllegalArgumentException.class, ()-> myString.encrypt(2,3));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: First convert digits example in the interface documentation
    public void testConvertDigitsToNamesInSubstring1() {
        myString.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        myString.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", myString.getString());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Convert digits to names in a substring with single digit
    public void testConvertDigitsToNamesInSubstring2() {
        myString.setString("A1B2C3D4E5");
        myString.convertDigitsToNamesInSubstring(8,8);
        assertEquals("A1B2C3DFourE5", myString.getString());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: <Add test description here>
    public void testConvertDigitsToNamesInSubstring3() {
        myString = null;
        assertThrows(NullPointerException.class, ()-> myString.convertDigitsToNamesInSubstring(2,5));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Throw IllegalArgumentException when FirstPosition > FinalPosition
    public void testConvertDigitsToNamesInSubstring4() {
        myString.setString("This is a test.");
        assertThrows(IllegalArgumentException.class, ()-> myString.convertDigitsToNamesInSubstring(10,6));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    // Description: Throws MyIndexOutOfBoundsException when FinalPosition is out of bounds
    public void testConvertDigitsToNamesInSubstring5() {
        myString.setString("This is a test.");
        assertThrows(MyIndexOutOfBoundsException.class, ()-> myString.convertDigitsToNamesInSubstring(1,20));
    }
}
