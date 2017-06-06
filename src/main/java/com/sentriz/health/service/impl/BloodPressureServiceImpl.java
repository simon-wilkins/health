package com.sentriz.health.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sentriz.health.domain.BloodPressure;
import com.sentriz.health.repository.BloodPressureRepository;
import com.sentriz.health.repository.search.BloodPressureSearchRepository;
import com.sentriz.health.service.BloodPressureService;

/**
 * Service Implementation for managing BloodPressure.
 */
@Service
@Transactional
public class BloodPressureServiceImpl implements BloodPressureService{

    private final Logger log = LoggerFactory.getLogger(BloodPressureServiceImpl.class);

    private final BloodPressureRepository bloodPressureRepository;

    private final BloodPressureSearchRepository bloodPressureSearchRepository;

    public BloodPressureServiceImpl(BloodPressureRepository bloodPressureRepository, BloodPressureSearchRepository bloodPressureSearchRepository) {
        this.bloodPressureRepository = bloodPressureRepository;
        this.bloodPressureSearchRepository = bloodPressureSearchRepository;
    }

    /**
     * Save a bloodPressure.
     *
     * @param bloodPressure the entity to save
     * @return the persisted entity
     */
    @Override
    public BloodPressure save(BloodPressure bloodPressure) {
        log.debug("Request to save BloodPressure : {}", bloodPressure);
        BloodPressure result = bloodPressureRepository.save(bloodPressure);
        bloodPressureSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the bloodPressures.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BloodPressure> findAll(Pageable pageable) {
        log.debug("Request to get all BloodPressures");
        Page<BloodPressure> result = bloodPressureRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one bloodPressure by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BloodPressure findOne(Long id) {
        log.debug("Request to get BloodPressure : {}", id);
        BloodPressure bloodPressure = bloodPressureRepository.findOne(id);
        return bloodPressure;
    }

    /**
     *  Delete the  bloodPressure by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BloodPressure : {}", id);
        bloodPressureRepository.delete(id);
        bloodPressureSearchRepository.delete(id);
    }

    /**
     * Search for the bloodPressure corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BloodPressure> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of BloodPressures for query {}", query);
        Page<BloodPressure> result = bloodPressureSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }

    @Override public List<BloodPressure> findAllByDateTimeBetweenAndUserLoginOrderByDateTimeDesc(LocalDate firstDate, LocalDate secondDate, String login) {
        return bloodPressureRepository.findAllByDateTimeBetweenAndUserLoginOrderByDateTimeDesc(firstDate, secondDate, login);
    }
}
