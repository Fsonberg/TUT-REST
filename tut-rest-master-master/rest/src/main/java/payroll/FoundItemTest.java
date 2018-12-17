package payroll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoundItemTest {

    FoundItem f = new FoundItem(
            "testD",
            "testE",
            "testF",
            false
    );

    @Before
    public void setUp() throws Exception {
        f.setEmpID(3L);
        f.setFoundItemID(4L);
    }

    @Test
    public void getEmpID() {
        Long expected = 3L;
        Long actual = f.getEmpID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that CustomerID " + expected + " -> CustomerID " + actual);
        System.out.println();
    }

    @Test
    public void setEmpID() {
        f.setEmpID(1L);
        Long expected = 1L;
        Long actual = f.getEmpID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that CustomerID " + expected + " -> CustomerID " + actual);
        System.out.println();
    }

    @Test
    public void isActive() {
        boolean expected = false;
        boolean actual = f.isActive();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Active " + expected + " -> Active " + actual);
        System.out.println();
    }

    @Test
    public void setActive() {
        f.setActive(true);
        boolean expected = true;
        boolean actual = f.isActive();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Active " + expected + " -> Active " + actual);
        System.out.println();
    }

    @Test
    public void getFoundItemID() {
        Long expected = 4L;
        Long actual = f.getFoundItemID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that LostItemID " + expected + " -> LostItemID " + actual);
        System.out.println();
    }

    @Test
    public void setFoundItemID() {
        f.setFoundItemID(22L);
        Long expected = 22L;
        Long actual = f.getFoundItemID();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that LostItemID " + expected + " -> LostItemID " + actual);
        System.out.println();
    }

    @Test
    public void getCategory() {
        String expected = "testD";
        String actual = f.getCategory();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Category " + expected + " -> Category " + actual);
        System.out.println();
    }

    @Test
    public void setCategory() {
        f.setCategory("categoryTst");
        String expected = "categoryTst";
        String actual = f.getCategory();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Category " + expected + " -> Category " + actual);
        System.out.println();
    }

    @Test
    public void getBrand() {
        String expected = "testE";
        String actual = f.getBrand();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Brand " + expected + " -> Brand " + actual);
        System.out.println();
    }

    @Test
    public void setBrand() {
        f.setBrand("brdTst");
        String expected = "brdTst";
        String actual = f.getBrand();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Brand " + expected + " -> Brand " + actual);
        System.out.println();
    }

    @Test
    public void setColor() {
        f.setColor("clrTst");
        String expected = "clrTst";
        String actual = f.getColor();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Color " + expected + " -> Color " + actual);
        System.out.println();

    }

    @Test
    public void getColor() {
        String expected = "testF";
        String actual = f.getColor();
        Assert.assertEquals(expected,actual);
        System.out.println("Testing that Color " + expected + " -> Color " + actual);
        System.out.println();
    }
}