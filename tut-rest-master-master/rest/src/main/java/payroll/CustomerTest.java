package payroll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
    Customer c = new Customer(
            "test",
            "testensen",
            "testVille",
            "11223344",
            "testTestensen@check.tst"
    );

    @Before
    public void setUp() throws Exception {

        c.setCustomerID(1L);
    }

    @Test
    public void getEmail() {
        String expected = "testTestensen@check.tst";
        String actual = c.getEmail();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setEmail() {
        c.setEmail("testing_set_email");
        String expected = "testing_set_email";
        String actual = c.getEmail();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void getCustomerID() {
        Long expected = 1L;
        Long actual = c.getCustomerID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setCustomerID() {
        c.setCustomerID(3L);
        Long expected = 3L;
        Long actual = c.getCustomerID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void getFirstName() {
        String expected = "test";
        String actual = c.getFirstName();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setFirstName() {
        c.setFirstName("doubleCheck");
        String expected = "doubleCheck";
        String actual = c.getFirstName();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void getLastName() {
        String expected = "testensen";
        String actual = c.getLastName();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setLastName() {
        c.setLastName("trippleCheck");
        String expected = "trippleCheck";
        String actual = c.getLastName();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();

    }

    @Test
    public void getAddress() {
        String expected = "testVille";
        String actual = c.getAddress();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setAddress() {
        c.setAddress("checkTestVille");
        String expected = "checkTestVille";
        String actual = c.getAddress();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void getPhoneNumber() {
        String expected = "11223344";
        String actual = c.getPhoneNumber();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setPhoneNumber() {
        c.setPhoneNumber("973-409-3313");
        String expected = "973-409-3313";
        String actual = c.getPhoneNumber();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }
}