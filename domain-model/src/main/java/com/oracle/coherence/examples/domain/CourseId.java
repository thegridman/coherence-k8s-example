package com.oracle.coherence.examples.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.tangosol.io.pof.schema.annotation.Portable;
import com.tangosol.io.pof.schema.annotation.PortableType;

import static com.oracle.coherence.examples.domain.PofTypes.POF_TYPE_COURSE;

/**
 * @author Jonathan Knight  2020.09.29
 */
@Embeddable
@PortableType(id = POF_TYPE_COURSE)
public class CourseId
        implements Serializable {

    @Portable
    private String classId;

    public CourseId() {
    }

    public CourseId(String classId) {
        this.classId = classId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseId courseId = (CourseId) o;
        return Objects.equals(classId, courseId.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }
}
