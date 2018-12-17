package payroll;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchTest {

    Match m = new Match(
            1L,
            2L,
            3L,
            4L);

    @Before
    public void setUp() throws Exception {
        m.setIssuedMatchID(22L);
    }

    @Test
    public void getIssuedMatchID() {
        Long expected = 22L;
        Long actual = m.getIssuedMatchID();
        assertEquals(expected,actual);
        System.out.println("Testing that issuedMatchID " + expected + " -> issuedMatchID " + actual);
        System.out.println();
    }

    @Test
    public void setIssuedMatchID() {
        m.setIssuedMatchID(11L);
        Long expected = 11L;
        Long actual = m.getIssuedMatchID();
        assertEquals(expected,actual);
        System.out.println("Testing that issuedMatchID " + expected + " -> issuedMatchID " + actual);
        System.out.println();
    }

    @Test
    public void getFoundItemID() {
        Long expected = 1L;
        Long actual = m.getFoundItemID();
        assertEquals(expected,actual);
        System.out.println("Testing that foundItemID " + expected + " -> foundItemID " + actual);
        System.out.println();
    }

    @Test
    public void setFoundItemID() {
        m.setFoundItemID(2220L);
        Long expected = 2220L;
        Long actual = m.getFoundItemID();
        assertEquals(expected,actual);
        System.out.println("Testing that foundItemID " + expected + " -> foundItemID " + actual);
        System.out.println();
    }

    @Test
    public void getLostItemID() {
        Long expected = 2L;
        Long actual = m.getLostItemID();
        assertEquals(expected,actual);
        System.out.println("Testing that lostItemID " + expected + " -> lostItemID " + actual);
        System.out.println();
    }

    @Test
    public void setLostItemID() {
        m.setLostItemID(2320L);
        Long expected = 2320L;
        Long actual = m.getLostItemID();
        assertEquals(expected,actual);
        System.out.println("Testing that lostItemID " + expected + " -> lostItemID " + actual);
        System.out.println();
    }

    @Test
    public void getCustomerID() {
        Long expected = 3L;
        Long actual = m.getCustomerID();
        assertEquals(expected,actual);
        System.out.println("Testing that customerID " + expected + " -> customerID " + actual);
        System.out.println();
    }

    @Test
    public void setCustomerID() {
        m.setCustomerID(155L);
        Long expected = 155L;
        Long actual = m.getCustomerID();
        assertEquals(expected,actual);
        System.out.println("Testing that customerID " + expected + " -> customerID " + actual);
        System.out.println();
    }

    @Test
    public void getEmpID() {
        Long expected = 4L;
        Long actual = m.getEmpID();
        assertEquals(expected,actual);
        System.out.println("Testing that empID " + expected + " -> empID " + actual);
        System.out.println();
    }

    @Test
    public void setEmpID() {
        m.setEmpID(245L);
        Long expected = 245L;
        Long actual = m.getEmpID();
        assertEquals(expected,actual);
        System.out.println("Testing that empID " + expected + " -> empID " + actual);
        System.out.println();
    }
}