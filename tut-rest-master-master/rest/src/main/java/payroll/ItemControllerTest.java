package payroll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class ItemControllerTest {
    Customer cTest = new Customer("testCustomerFirstname","testCustomerLastName",
            "testCustomerAdress","12345678","testCustomerEmail");
    Customer cTest3 = new Customer("testCustomerFirstname","testCustomerLastName",
            "testCustomerAdress","12345678","testCustomerEmail");
    Customer cTest4 = new Customer("testCustomerFirstname","testCustomerLastName",
            "testCustomerAdress","12345678","testCustomerEmail");

    Employee eTest = new Employee("testEmpFirstName","testEmployeeLastName",
            "testEmployeeAddress","123456789","testEmployeeEmail");
    Employee eTest3 = new Employee("testEmpFirstName","testEmployeeLastName",
            "testEmployeeAddress","123456789","testEmployeeEmail");
    Employee eTest4 = new Employee("testEmpFirstName","testEmployeeLastName",
            "testEmployeeAddress","123456789","testEmployeeEmail");

    LostItem lTest = new LostItem("Electronics","Apple","Black",true);
    LostItem lTest2 = new LostItem("Electronics","Apple","Black", false);
    LostItem lTest3 = new LostItem("Clothes", "Gucci", "Yellow", false);

    FoundItem fTest = new FoundItem("Electronics", "Apple", "Black", true);
    FoundItem fTest2 = new FoundItem("Electronics", "Apple", "Black", false);
    FoundItem fTest3 = new FoundItem("Clothes", "Hugo Boss", "Grey", true);



    ArrayList<Customer> customerTestList = new ArrayList<>();
    ArrayList<Employee> employeeTestList = new ArrayList<>();
    ArrayList<LostItem> lostItemTestList = new ArrayList<>();
    ArrayList<FoundItem> foundItemTestList = new ArrayList<>();



    int setUpSize =10;
    @Before
    public void setUp() throws Exception {
        cTest.setCustomerID(1L);

        eTest.setEmpID(2L);
        eTest3.setEmpID(10L);
        eTest4.setEmpID(11L);

        lTest.setLostItemID(3L);
        lTest2.setLostItemID(4L);
        lTest3.setLostItemID(5L);

        fTest.setFoundItemID(6L);
        fTest2.setFoundItemID(7L);
        fTest3.setFoundItemID(8L);
    }


    @Test
    public void newEmployee() {
        Employee eTest2 = new Employee("newEmployeeTest","newEmployeeTest",
                "newEmployeeTest","1231456","newEmployeeTest");
        eTest2.setEmpID(9L);
        employeeTestList.add(eTest2);
        //System.out.println("Testing newEmployee" + employeeTestList);

        Assert.assertEquals(eTest2,employeeTestList.get(0));
    }

    @Test
    public void allEmployees() {
        // Not sure, if and how we should test this
        employeeTestList.add(eTest);
        employeeTestList.add(eTest3);
        employeeTestList.add(eTest4);
        for (int i = 0; i <employeeTestList.size() ; i++) {
           // System.out.println(employeeTestList.get(i))
        }
        Assert.assertEquals(employeeTestList.get(0),eTest);
        Assert.assertEquals(employeeTestList.get(1),eTest3);
        Assert.assertEquals(employeeTestList.get(2),eTest4);
    }

    @Test
    public void singleEmployeeID() {
        employeeTestList.add(eTest);
        employeeTestList.add(eTest3);
        employeeTestList.add(eTest4);
        //System.out.println("single employee: " + employeeTestList.get(2).getEmpID());

        Assert.assertEquals(employeeTestList.get(2).getEmpID(), eTest3.getEmpID());

    }

    /**
     * Tænker muligvis at droppe denne test med en eller flere --> Virker redundant?
     */
    @Test
    public void oneOrMoreEmployee() {
        employeeTestList.add(eTest);
        employeeTestList.add(eTest3);
        employeeTestList.add(eTest4);
        int oneOrMore = 2;
        for (int i = 0; i <oneOrMore ; i++) {
            //System.out.println("One or More Employee " + employeeTestList.get(i));
        }
    }

    @Test
    public void newCustomer() {
        Customer cTest2 = new Customer("newCustomerTest","newCustomerTest",
                "newCustomerTest","1231456","newCustomerTest");
        cTest2.setCustomerID(12L);
        customerTestList.add(cTest2);
        Assert.assertEquals(cTest2,customerTestList);
        //System.out.println("Testing newCustomer" + customerTestList);
    }

    @Test
    public void allCustomers() {
        // Not sure, if and how we should test this
        customerTestList.add(cTest);
        customerTestList.add(cTest3);
        customerTestList.add(cTest4);
        for (int i = 0; i <customerTestList.size() ; i++) {
            System.out.println(customerTestList.get(i));
        }
        Assert.assertEquals(customerTestList.get(0),cTest);
        Assert.assertEquals(customerTestList.get(1),cTest3);
        Assert.assertEquals(customerTestList.get(2),cTest4);
    }

    @Test
    public void singleCustomerID() {
        customerTestList.add(cTest);
        customerTestList.add(cTest3);
        customerTestList.add(cTest4);
        System.out.println("Testing singleCustomer" + customerTestList.get(2).getCustomerID());

        Assert.assertEquals(cTest3.getCustomerID(), customerTestList.get(2).getCustomerID());
    }

    @Test
    public void oneOrMoreCustomers() {
    }

    @Test
    public void newFoundItem() {
        FoundItem fTest4 = new FoundItem("Electronics","BlackBerry","Black",true);
        fTest4.setFoundItemID(13L);
        foundItemTestList.add(fTest4);

        Assert.assertEquals(fTest4, foundItemTestList.get(0));

    }

    @Test
    public void allFound() {
        foundItemTestList.add(fTest);
        foundItemTestList.add(fTest2);
        foundItemTestList.add(fTest3);
        for (int i = 0; i <foundItemTestList.size() ; i++) {
            System.out.println(foundItemTestList);
        }
        Assert.assertEquals(foundItemTestList.get(0),fTest);
        Assert.assertEquals(foundItemTestList.get(1),fTest2);
        Assert.assertEquals(foundItemTestList.get(2),fTest3);

    }

    @Test
    public void allFoundActive() {

    }

    @Test
    public void allFoundDisabled() {
    }

    @Test
    public void foundItemID() {
    }

    @Test
    public void foundItemSearch() {
    }

    @Test
    public void newItem() {
        LostItem lTest4 = new LostItem("Electronics", "BlackBerry", "Black", true);
        lTest4.setLostItemID(14L);
        lostItemTestList.add(lTest4);
        System.out.println(lostItemTestList);
        Assert.assertEquals(lTest4, lostItemTestList);
    }

    @Test
    public void allLost() {
        lostItemTestList.add(lTest);
        lostItemTestList.add(lTest2);
        lostItemTestList.add(lTest3);
        for (int i = 0; i <lostItemTestList.size() ; i++) {
            System.out.println(lostItemTestList.get(i));
        }
        Assert.assertEquals(lTest,lostItemTestList.get(0));
        Assert.assertEquals(lTest2,lostItemTestList.get(1));
        Assert.assertEquals(lTest3,lostItemTestList.get(2));
    }

    @Test
    public void allLostActive() {
    }

    @Test
    public void allLostDisabled() {
    }

    @Test
    public void lostItemID() {
    }

    @Test
    public void lostItemSearch() {
    }

    @Test
    public void matchLostFound() {
        foundItemTestList.add(fTest);
        foundItemTestList.add(fTest2);
        foundItemTestList.add(fTest3);
        lostItemTestList.add(lTest);
        lostItemTestList.add(lTest2);
        lostItemTestList.add(lTest3);
        for (int i = 0; i <lostItemTestList.size() ; i++) {
            for (int j = 0; j <foundItemTestList.size() ; j++) {
                Assert.assertEquals(lostItemTestList.get(i).getCategory(),foundItemTestList.get(i).getCategory());
                Assert.assertEquals(lostItemTestList.get(i).getBrand(),foundItemTestList.get(i).getBrand());
                Assert.assertEquals(lostItemTestList.get(i).getColor(),foundItemTestList.get(i).getColor());
                System.out.println(lostItemTestList.get(i).getLostItemID() + foundItemTestList.get(i).getFoundItemID() + "\n");
            }

        }
    }

    @Test
    public void issueAMatch() {
        foundItemTestList.add(fTest);
        foundItemTestList.add(fTest2);
        foundItemTestList.add(fTest3);
        lostItemTestList.add(lTest);
        lostItemTestList.add(lTest2);
        lostItemTestList.add(lTest3);
        for (int i = 0; i <lostItemTestList.size() ; i++) {
            for (int j = 0; j <foundItemTestList.size() ; j++) {

                if (lostItemTestList.get(i).getCategory().equals(foundItemTestList.get(i).getCategory())&&lostItemTestList.get(i).getBrand().equals(foundItemTestList.get(i).getBrand())
                        && lostItemTestList.get(i).getColor().equals(foundItemTestList.get(i).getColor())){
                    lostItemTestList.get(i).setActive(false);
                    foundItemTestList.get(i).setActive(false);
                }else {lostItemTestList.get(i).setActive(true); foundItemTestList.get(i).setActive(true);}

                Assert.assertEquals(lostItemTestList.get(i).isActive(),false);
                Assert.assertEquals(foundItemTestList.get(i).isActive(),false);
                System.out.println("lost: " + lostItemTestList.get(i).isActive() + " found: " + foundItemTestList.get(i).isActive());
            }

        }
    }

    @Test
    public void issuedMatches() {
    }

    @Test
    public void issuedMatchesSearch() {
    }

    @Test
    public void replaceLostItem() {
    }

    @Test
    public void replaceFoundItem() {
    }

    @Test
    public void replaceCustomerInfo() {
    }

    @Test
    public void replaceEmployeeInfo() {
    }

    @Test
    public void replaceIssuedMatchInfo() {
    }

    @Test
    public void deleteLostItem() {
    }

    @Test
    public void deleteFoundItem() {
    }

    @Test
    public void deleteCustomer() {
    }

    @Test
    public void deleteEmployee() {
    }
}