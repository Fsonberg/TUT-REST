package payroll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {
    Employee e = new Employee("empTest",
            "empTestensen",
            "empTstAddresss",
            "12233456",
            "empEmail@testing.tst");

    @Before
    public void setUp() throws Exception {
        e.setEmpID(1L);
    }

    @Test
    public void getEmpID() {
        Long expected = 1L;
        Long actual = e.getEmpID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();

    }

    @Test
    public void setEmpID() {
        e.setEmpID(3L);
        Long expected = 3L;
        Long actual = e.getEmpID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void getFirstName() {
        String expected = "empTest";
        String actual = e.getFirstName();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setFirstName() {
        e.setFirstName("empNametst");
        String expected = "empNametst";
        String actual = e.getFirstName();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void getLastName() {
        String expected = "empTestensen";
        String actual = e.getLastName();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setLastName() {
        e.setLastName("empLnTst");
        String expected = "empLnTst";
        String actual = e.getLastName();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void getAddress() {
        String expected = "empTstAddresss";
        String actual = e.getAddress();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setAddress() {
        e.setAddress("checkemptstAdd");
        String expected = "checkemptstAdd";
        String actual = e.getAddress();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();

    }

    @Test
    public void getPhoneNumber() {
        String expected = "12233456";
        String actual = e.getPhoneNumber();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();

    }

    @Test
    public void setPhoneNumber() {
        e.setPhoneNumber("973-409-3252");
        String expected = "973-409-3252";
        String actual = e.getPhoneNumber();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void getEmail() {
        String expected = "empEmail@testing.tst";
        String actual = e.getEmail();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }

    @Test
    public void setEmail() {
        e.setEmail("empEmailTest");
        String expected = "empEmailTest";
        String actual = e.getEmail();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that " + expected + " -> " + actual);
        System.out.println();
    }
}