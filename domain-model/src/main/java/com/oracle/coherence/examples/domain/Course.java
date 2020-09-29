package com.oracle.coherence.examples.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.tangosol.io.pof.schema.annotation.Portable;
import com.tangosol.io.pof.schema.annotation.PortableType;

import static com.oracle.coherence.examples.domain.PofTypes.POF_TYPE_COURSE;

/**
 * @author Jonathan Knight  2020.09.29
 */
@Entity
@PortableType(id = POF_TYPE_COURSE)
public class Course {

    @EmbeddedId
    @Portable
    private CourseId id;

    @Portable
    private String name;

    public Course() {
    }

    public Course(CourseId id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseId getId() {
        return id;
    }

    public void setId(CourseId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
