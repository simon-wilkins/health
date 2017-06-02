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

import com.sentriz.health.domain.Points;
import com.sentriz.health.repository.PointsRepository;
import com.sentriz.health.repository.search.PointsSearchRepository;
import com.sentriz.health.service.PointsService;

/**
 * Service Implementation for managing Points.
 */
@Service
@Transactional
public class PointsServiceImpl implements PointsService{

    private final Logger log = LoggerFactory.getLogger(PointsServiceImpl.class);

    private final PointsRepository pointsRepository;

    private final PointsSearchRepository pointsSearchRepository;

    public PointsServiceImpl(PointsRepository pointsRepository, PointsSearchRepository pointsSearchRepository) {
        this.pointsRepository = pointsRepository;
        this.pointsSearchRepository = pointsSearchRepository;
    }

    /**
     * Save a points.
     *
     * @param points the entity to save
     * @return the persisted entity
     */
    @Override
    public Points save(Points points) {
        log.debug("Request to save Points : {}", points);
        Points result = pointsRepository.save(points);
        pointsSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the points.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Points> findAll(Pageable pageable) {
        log.debug("Request to get all Points");
        Page<Points> result = pointsRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one points by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Points findOne(Long id) {
        log.debug("Request to get Points : {}", id);
        Points points = pointsRepository.findOne(id);
        return points;
    }

    /**
     *  Delete the  points by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Points : {}", id);
        pointsRepository.delete(id);
        pointsSearchRepository.delete(id);
    }

    /**
     * Search for the points corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Points> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Points for query {}", query);
        Page<Points> result = pointsSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Points> findByUserIsCurrentUser(Pageable pageable) {
        return pointsRepository.findByUserIsCurrentUser(pageable);
    }

    @Override public List<Points> findAllByDateBetween(LocalDate startOfWeek, LocalDate endOfWeek) {
        return pointsRepository.findAllByDateBetween(startOfWeek, endOfWeek);
    }
}
