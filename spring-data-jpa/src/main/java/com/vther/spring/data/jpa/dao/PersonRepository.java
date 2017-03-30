package com.vther.spring.data.jpa.dao;

import com.vther.spring.data.jpa.entity.dbunit.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Set<Person> findByName(String name);

    @Query(value = "FROM Person p WHERE p.name like :pattern")
    Set<Person> search(@Param("pattern") String pattern);

}
