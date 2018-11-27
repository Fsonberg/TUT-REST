package payroll;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Users {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String adress;
    
}
