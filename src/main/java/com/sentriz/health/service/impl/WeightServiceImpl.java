package com.sentriz.health.service.impl;

import com.sentriz.health.service.WeightService;
import com.sentriz.health.domain.Weight;
import com.sentriz.health.repository.WeightRepository;
import com.sentriz.health.repository.search.WeightSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Weight.
 */
@Service
@Transactional
public class WeightServiceImpl implements WeightService{

    private final Logger log = LoggerFactory.getLogger(WeightServiceImpl.class);
    
    private final WeightRepository weightRepository;

    private final WeightSearchRepository weightSearchRepository;

    public WeightServiceImpl(WeightRepository weightRepository, WeightSearchRepository weightSearchRepository) {
        this.weightRepository = weightRepository;
        this.weightSearchRepository = weightSearchRepository;
    }

    /**
     * Save a weight.
     *
     * @param weight the entity to save
     * @return the persisted entity
     */
    @Override
    public Weight save(Weight weight) {
        log.debug("Request to save Weight : {}", weight);
        Weight result = weightRepository.save(weight);
        weightSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the weights.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Weight> findAll(Pageable pageable) {
        log.debug("Request to get all Weights");
        Page<Weight> result = weightRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one weight by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Weight findOne(Long id) {
        log.debug("Request to get Weight : {}", id);
        Weight weight = weightRepository.findOne(id);
        return weight;
    }

    /**
     *  Delete the  weight by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Weight : {}", id);
        weightRepository.delete(id);
        weightSearchRepository.delete(id);
    }

    /**
     * Search for the weight corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Weight> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Weights for query {}", query);
        Page<Weight> result = weightSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
