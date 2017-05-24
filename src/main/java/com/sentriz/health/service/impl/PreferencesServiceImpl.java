package com.sentriz.health.service.impl;

import com.sentriz.health.service.PreferencesService;
import com.sentriz.health.domain.Preferences;
import com.sentriz.health.repository.PreferencesRepository;
import com.sentriz.health.repository.search.PreferencesSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Preferences.
 */
@Service
@Transactional
public class PreferencesServiceImpl implements PreferencesService{

    private final Logger log = LoggerFactory.getLogger(PreferencesServiceImpl.class);
    
    private final PreferencesRepository preferencesRepository;

    private final PreferencesSearchRepository preferencesSearchRepository;

    public PreferencesServiceImpl(PreferencesRepository preferencesRepository, PreferencesSearchRepository preferencesSearchRepository) {
        this.preferencesRepository = preferencesRepository;
        this.preferencesSearchRepository = preferencesSearchRepository;
    }

    /**
     * Save a preferences.
     *
     * @param preferences the entity to save
     * @return the persisted entity
     */
    @Override
    public Preferences save(Preferences preferences) {
        log.debug("Request to save Preferences : {}", preferences);
        Preferences result = preferencesRepository.save(preferences);
        preferencesSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the preferences.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Preferences> findAll() {
        log.debug("Request to get all Preferences");
        List<Preferences> result = preferencesRepository.findAll();

        return result;
    }

    /**
     *  Get one preferences by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Preferences findOne(Long id) {
        log.debug("Request to get Preferences : {}", id);
        Preferences preferences = preferencesRepository.findOne(id);
        return preferences;
    }

    /**
     *  Delete the  preferences by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Preferences : {}", id);
        preferencesRepository.delete(id);
        preferencesSearchRepository.delete(id);
    }

    /**
     * Search for the preferences corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Preferences> search(String query) {
        log.debug("Request to search Preferences for query {}", query);
        return StreamSupport
            .stream(preferencesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
