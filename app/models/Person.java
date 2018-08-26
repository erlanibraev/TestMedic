package models;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

	public String iin;

    public String firstName;
    public String lastName;
    public String middleName;
}
