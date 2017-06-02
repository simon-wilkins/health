package com.sentriz.health.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sentriz.health.domain.Points;

/**
 * Service Interface for managing Points.
 */
public interface PointsService {

    /**
     * Save a points.
     *
     * @param points the entity to save
     * @return the persisted entity
     */
    Points save(Points points);

    /**
     *  Get all the points.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Points> findAll(Pageable pageable);

    /**
     *  Get the "id" points.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Points findOne(Long id);

    /**
     *  Delete the "id" points.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the points corresponding to the query.
     *
     *  @param query the query of the search
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Points> search(String query, Pageable pageable);

    Page<Points> findByUserIsCurrentUser(Pageable pageable);

    List<Points> findAllByDateBetween(LocalDate startOfWeek, LocalDate endOfWeek);
}
