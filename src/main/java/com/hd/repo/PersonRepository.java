package com.hd.repo;

import com.hd.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hdereli
 * @since 24/7/2023
 */

public interface PersonRepository extends JpaRepository<Person, Long> {
}
