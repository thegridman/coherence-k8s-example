package com.oracle.coherence.examples.storage;

import com.oracle.coherence.examples.domain.Student;
import com.oracle.coherence.examples.domain.StudentId;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

/**
 * @author Jonathan Knight  2020.09.10
 */
@Repository("students")
public interface StudentRepository
        extends CrudRepository<Student, StudentId> {
}
