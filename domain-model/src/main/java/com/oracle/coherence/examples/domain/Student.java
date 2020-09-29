package com.oracle.coherence.examples.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

import com.tangosol.io.pof.schema.annotation.Portable;
import com.tangosol.io.pof.schema.annotation.PortableType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import static com.oracle.coherence.examples.domain.PofTypes.POF_TYPE_STUDENT;

/**
 * @author Jonathan Knight  2020.09.09
 */
@Entity
@PortableType(id = POF_TYPE_STUDENT)
public class Student {

    @EmbeddedId
    @Portable
    private StudentId id;

    @Portable
    private String firstName;

    @Portable
    private String lastName;

    @Portable
    private String className;

    @OneToOne()
    @Cascade(CascadeType.ALL)
    @Portable
    private Address address;

    /**
     * Required by JPA.
     */
    public Student() {
    }

    public Student(String rollNumber, String firstName, String lastName,
                   String className, Address address) {
        this.id = new StudentId(rollNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        setAddress(address);
    }

    public StudentId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRollNumber() {
        return id.roll;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if (this.address != null) {
            this.address.setRoll(this.id.getRoll());
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", className='" + className + '\'' +
                ", address=" + address +
                '}';
    }
}
