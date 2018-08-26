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
    public Long personId;
    public Long organizationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="personId", nullable = true, insertable = false, updatable = false)
    public Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="organizationId", nullable = true, insertable = false, updatable = false)
    public Organization organization;


}
