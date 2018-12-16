package payroll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LostItemTest {

    LostItem l = new LostItem(
            "test",
            "testB",
            "TestC",
            true
    );

    @Before
    public void setUp() throws Exception {
        l.setCustomerID(1L);
        l.setLostItemID(2L);
    }

    @Test
    public void getCustomerID() {
        Long expected = 1L;
        Long actual = l.getCustomerID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that CustomerID " + expected + " -> CustomerID " + actual);
        System.out.println();
    }

    @Test
    public void setCustomerID() {
        l.setCustomerID(3L);
        Long expected = 3L;
        Long actual = l.getCustomerID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that CustomerID " + expected + " -> CustomerID " + actual);
        System.out.println();
    }

    @Test
    public void isActive() {
        boolean expected = true;
        boolean actual = l.isActive();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Active " + expected + " -> Active " + actual);
        System.out.println();
    }

    @Test
    public void setActive() {
        l.setActive(false);
        boolean expected = false;
        boolean actual = l.isActive();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Active " + expected + " -> Active " + actual);
        System.out.println();
    }

    @Test
    public void getLostItemID() {
        Long expected = 2L;
        Long actual = l.getLostItemID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that LostItemID " + expected + " -> LostItemID " + actual);
        System.out.println();
    }

    @Test
    public void setLostItemID() {
        l.setLostItemID(4L);
        Long expected = 4L;
        Long actual = l.getLostItemID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that LostItemID " + expected + " -> LostItemID " + actual);
        System.out.println();
    }

    @Test
    public void getCategory() {
        String expected = "test";
        String actual = l.getCategory();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Category " + expected + " -> Category " + actual);
        System.out.println();
    }

    @Test
    public void setCategory() {
        l.setCategory("testingCategory");
        String expected = "testingCategory";
        String actual = l.getCategory();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Category " + expected + " -> Category " + actual);
        System.out.println();
    }

    @Test
    public void getBrand() {
        String expected = "testB";
        String actual = l.getBrand();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Brand " + expected + " -> Brand " + actual);
        System.out.println();
    }

    @Test
    public void setBrand() {
        l.setBrand("testingBrand");
        String expected = "testingBrand";
        String actual = l.getBrand();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Brand " + expected + " -> Brand " + actual);
        System.out.println();

    }

    @Test
    public void getColor() {
        String expected = "TestC";
        String actual = l.getColor();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Color " + expected + " -> Color " + actual);
        System.out.println();
    }

    @Test
    public void setColor() {
        l.setColor("testingColor");
        String expected = "testingColor";
        String actual = l.getColor();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Color " + expected + " -> Color " + actual);
        System.out.println();

    }
}