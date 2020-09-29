package com.oracle.coherence.examples.storage;

import com.oracle.coherence.examples.domain.Student;
import com.oracle.coherence.examples.domain.StudentId;

import com.tangosol.net.cache.CacheStore;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Jonathan Knight  2020.09.29
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
public class StudentCacheStoreIT {

    /**
     * Injected by Spring.
     */
    @Autowired
    private StudentRepository repository;

    @Test
    public void shouldLoadStudentThatDoesNotExistInDB() {
        CacheStore<StudentId, Student> store = new SpringJpaCacheStore<>(repository);
        Student student = store.load(new StudentId("9876"));
        assertThat(student, is(nullValue()));
    }

    @Test
    @DatabaseSetup("/student.xml")
    public void shouldLoadStudentThatExistsInDB() {
        CacheStore<StudentId, Student> store = new SpringJpaCacheStore<>(repository);
        Student student = store.load(new StudentId("1234"));
        assertThat(student, is(notNullValue()));
        assertThat(student.getFirstName(), is("Zaphod"));
    }

    @Test
    public void shouldStoreStudent() {
        Student student = new Student("1234", "Zaphod", "Beeblebrox", "Astronomy", null);
        CacheStore<StudentId, Student> store = new SpringJpaCacheStore<>(repository);
        store.store(student.getId(), student);
        Student loaded = store.load(student.getId());
        assertThat(loaded, is(notNullValue()));
    }

    @Test
    @DatabaseSetup("/student.xml")
    public void shouldEraseStudent() {
        CacheStore<StudentId, Student> store = new SpringJpaCacheStore<>(repository);
        StudentId id = new StudentId("1234");
        store.erase(id);
        Student loaded = store.load(id);
        assertThat(loaded, is(nullValue()));
    }
}
