package com.ex.base.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer restaurant_id;
    private Integer customer_id;
    private Integer reservation_id;
    private Integer score;
    private String content;

    public Reviews() {
    }

    public Reviews(Integer restaurant_id, Integer customer_id, Integer reservation_id, Integer score, String content) {
        this.restaurant_id = restaurant_id;
        this.customer_id = customer_id;
        this.reservation_id = reservation_id;
        this.score = score;
        this.content = content;
    }

    public Long getID() { return id; }

    public void setID(Long id) { this.id = id; }

    public Integer getRestaurantId() { return restaurant_id; }

    public void setRestaurantId(Integer restaurant_id) { this.restaurant_id = restaurant_id; }

    public Integer getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getReservationId() {
        return reservation_id;
    }

    public void setReservationId(Integer reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Integer getScore() { return score; }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}