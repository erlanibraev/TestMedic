package models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Erlan Ibraev on 26.08.2018.
 */
@Entity
public class Attaching {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public Date created;
    public Date processed;
    public String status;

    @ManyToOne
    @JoinColumn(name="person_id", nullable = true)
    public Person person;

    @ManyToOne
    @JoinColumn(name="organization_id", nullable = true)
    public Organization organization;


}
