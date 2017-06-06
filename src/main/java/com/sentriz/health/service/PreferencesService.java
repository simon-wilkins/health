package com.sentriz.health.service;

import java.util.List;
import java.util.Optional;

import com.sentriz.health.domain.Preferences;

/**
 * Service Interface for managing Preferences.
 */
public interface PreferencesService {

    /**
     * Save a preferences.
     *
     * @param preferences the entity to save
     * @return the persisted entity
     */
    Preferences save(Preferences preferences);

    /**
     *  Get all the preferences.
     *
     *  @return the list of entities
     */
    List<Preferences> findAll();

    /**
     *  Get the "id" preferences.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Preferences findOne(Long id);

    /**
     *  Delete the "id" preferences.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the preferences corresponding to the query.
     *
     *  @param query the query of the search
     *
     *  @return the list of entities
     */
    List<Preferences> search(String query);

    Optional<Preferences> findOneByUserLogin(String login);
}
