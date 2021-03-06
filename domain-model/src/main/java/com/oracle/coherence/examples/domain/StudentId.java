package com.oracle.coherence.examples.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.tangosol.io.pof.schema.annotation.Portable;
import com.tangosol.io.pof.schema.annotation.PortableType;

import static com.oracle.coherence.examples.domain.PofTypes.POF_TYPE_STUDENT_ID;

/**
 * @author Jonathan Knight  2020.09.09
 */
@Embeddable
@PortableType(id = POF_TYPE_STUDENT_ID)
public class StudentId
        implements Serializable {

    @Portable
    String roll;

    public StudentId() {
    }

    public StudentId(String roll) {
        this.roll = roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getRoll() {
        return roll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentId studentId = (StudentId) o;
        return Objects.equals(roll, studentId.roll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roll);
    }
}
