import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;

import static org.junit.Assert.*;

/**
 * A test case for marketplace
 */
public class MarketplaceTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        System.out.printf("Test Count: %d.\n", result.getRunCount());
        if (result.wasSuccessful()) {
            System.out.printf("Excellent - all local tests ran successfully.\n");
        } else {
            System.out.printf("Tests failed: %d.\n", result.getFailureCount());
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    /**
     * The test case for marketplace
     *
     * @author Isaac Fuksman and Purdue CS
     *
     * @version November 13, 2022
     */
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }


        @Test(timeout = 1000)
        public void testExpectedOne() {

            // Set the input
            String input = "2\n1\ntest@email.com\ntestPass\ntestPass\nTest\n2"; //THIS

            // Pair the input with the expected result
            String expected = "Sign in or create an account?" + System.lineSeparator() + "1. Sign in" +
                    System.lineSeparator() + "2. Create an account" + System.lineSeparator() +
                    "Are you a customer or seller?" + System.lineSeparator() + "1. Customer" +
                    System.lineSeparator() + "2. Seller" + System.lineSeparator() + "Please enter your email: "
                    + System.lineSeparator() + "Please enter your new password: " + System.lineSeparator() +
                    "Please verify your password: " + System.lineSeparator() + "What is your name?" +
                    System.lineSeparator() + "Marketplace is currently closed." + System.lineSeparator() +
                    "Would you like to update your account?" + System.lineSeparator() + "1. Yes" +
                    System.lineSeparator() + "2. No" + System.lineSeparator() +
                    "Thank you for using our marketplace!";

            // Runs the program with the input values
            receiveInput(input);
            Marketplace.main(new String[0]); //Class

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Test case failed.",
                    expected.trim(), output.trim());
        }



    }
}