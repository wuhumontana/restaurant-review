package com.ex.base.jpa;

import com.ex.base.entity.Reviews;
import org.springframework.data.repository.CrudRepository;

public interface myReviewsRepository extends CrudRepository<Reviews, Long> {
}