package com.sentriz.health.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sentriz.health.domain.Preferences;

/**
 * Spring Data JPA repository for the Preferences entity.
 */
@SuppressWarnings("unused")
public interface PreferencesRepository extends JpaRepository<Preferences,Long> {

    Optional<Preferences> findOneByUserLogin(String login);

}
