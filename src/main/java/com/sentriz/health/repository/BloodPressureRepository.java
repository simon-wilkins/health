package com.sentriz.health.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sentriz.health.domain.BloodPressure;

/**
 * Spring Data JPA repository for the BloodPressure entity.
 */
@SuppressWarnings("unused")
public interface BloodPressureRepository extends JpaRepository<BloodPressure,Long> {

    @Query("select bloodPressure from BloodPressure bloodPressure where bloodPressure.user.login = ?#{principal.username}")
    List<BloodPressure> findByUserIsCurrentUser();

    List<BloodPressure> findAllByDateTimeBetweenAndUserLoginOrderByDateTimeDesc(LocalDate firstDate, LocalDate secondDate, String login);

}
