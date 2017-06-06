package com.sentriz.health.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.sentriz.health.domain.BloodPressure;
import com.sentriz.health.security.SecurityUtils;
import com.sentriz.health.service.BloodPressureService;
import com.sentriz.health.web.rest.util.HeaderUtil;
import com.sentriz.health.web.rest.util.PaginationUtil;
import com.sentriz.health.web.rest.vm.BloodPressureByPeriod;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing BloodPressure.
 */
@RestController
@RequestMapping("/api")
public class BloodPressureResource {

    private final Logger log = LoggerFactory.getLogger(BloodPressureResource.class);

    private static final String ENTITY_NAME = "bloodPressure";

    private final BloodPressureService bloodPressureService;

    public BloodPressureResource(BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }

    /**
     * POST  /blood-pressures : Create a new bloodPressure.
     *
     * @param bloodPressure the bloodPressure to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bloodPressure, or with status 400 (Bad Request) if the bloodPressure has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/blood-pressures")
    @Timed
    public ResponseEntity<BloodPressure> createBloodPressure(@Valid @RequestBody BloodPressure bloodPressure) throws URISyntaxException {
        log.debug("REST request to save BloodPressure : {}", bloodPressure);
        if (bloodPressure.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bloodPressure cannot already have an ID")).body(null);
        }
        BloodPressure result = bloodPressureService.save(bloodPressure);
        return ResponseEntity.created(new URI("/api/blood-pressures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /blood-pressures : Updates an existing bloodPressure.
     *
     * @param bloodPressure the bloodPressure to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bloodPressure,
     * or with status 400 (Bad Request) if the bloodPressure is not valid,
     * or with status 500 (Internal Server Error) if the bloodPressure couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/blood-pressures")
    @Timed
    public ResponseEntity<BloodPressure> updateBloodPressure(@Valid @RequestBody BloodPressure bloodPressure) throws URISyntaxException {
        log.debug("REST request to update BloodPressure : {}", bloodPressure);
        if (bloodPressure.getId() == null) {
            return createBloodPressure(bloodPressure);
        }
        BloodPressure result = bloodPressureService.save(bloodPressure);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bloodPressure.getId().toString()))
            .body(result);
    }

    /**
     * GET  /blood-pressures : get all the bloodPressures.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bloodPressures in body
     */
    @GetMapping("/blood-pressures")
    @Timed
    public ResponseEntity<List<BloodPressure>> getAllBloodPressures(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of BloodPressures");
        Page<BloodPressure> page = bloodPressureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/blood-pressures");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /blood-pressures/:id : get the "id" bloodPressure.
     *
     * @param id the id of the bloodPressure to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bloodPressure, or with status 404 (Not Found)
     */
    @GetMapping("/blood-pressures/{id}")
    @Timed
    public ResponseEntity<BloodPressure> getBloodPressure(@PathVariable Long id) {
        log.debug("REST request to get BloodPressure : {}", id);
        BloodPressure bloodPressure = bloodPressureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bloodPressure));
    }

    /**
     * DELETE  /blood-pressures/:id : delete the "id" bloodPressure.
     *
     * @param id the id of the bloodPressure to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/blood-pressures/{id}")
    @Timed
    public ResponseEntity<Void> deleteBloodPressure(@PathVariable Long id) {
        log.debug("REST request to delete BloodPressure : {}", id);
        bloodPressureService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/blood-pressures?query=:query : search for the bloodPressure corresponding
     * to the query.
     *
     * @param query the query of the bloodPressure search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/blood-pressures")
    @Timed
    public ResponseEntity<List<BloodPressure>> searchBloodPressures(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of BloodPressures for query {}", query);
        Page<BloodPressure> page = bloodPressureService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/blood-pressures");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /bp-by-days : get all the blood pressure readings by last x days.
     */
    @RequestMapping(value = "/bp-by-days/{days}")
    @Timed
    public ResponseEntity<BloodPressureByPeriod> getByDays(@PathVariable int days) {
        LocalDate rightNow = LocalDate.now();
        LocalDate daysAgo = rightNow.minusDays(days);
        List<BloodPressure> readings = bloodPressureService.findAllByDateTimeBetweenAndUserLoginOrderByDateTimeDesc(daysAgo, rightNow, SecurityUtils.getCurrentUserLogin());
        BloodPressureByPeriod response = new BloodPressureByPeriod("Last " + days + " Days", readings);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
