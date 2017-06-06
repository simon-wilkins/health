package com.sentriz.health.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * A BloodPressure.
 */
@Entity
@Table(name = "blood_pressure")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "bloodpressure")
public class BloodPressure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDate dateTime;

    @NotNull
    @Min(value = 0)
    @Column(name = "systolic", nullable = false)
    private Integer systolic;

    @NotNull
    @Min(value = 0)
    @Column(name = "diastolic", nullable = false)
    private Integer diastolic;

    @ManyToOne
    private User user;

    public BloodPressure() {}

    public BloodPressure(LocalDate dateTime, Integer systolic, Integer diastolic, User user) {
        this.dateTime = dateTime;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public BloodPressure dateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public BloodPressure systolic(Integer systolic) {
        this.systolic = systolic;
        return this;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public BloodPressure diastolic(Integer diastolic) {
        this.diastolic = diastolic;
        return this;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }

    public User getUser() {
        return user;
    }

    public BloodPressure user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BloodPressure bloodPressure = (BloodPressure) o;
        if (bloodPressure.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bloodPressure.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BloodPressure{" +
            "id=" + getId() +
            ", dateTime='" + getDateTime() + "'" +
            ", systolic='" + getSystolic() + "'" +
            ", diastolic='" + getDiastolic() + "'" +
            "}";
    }
}
