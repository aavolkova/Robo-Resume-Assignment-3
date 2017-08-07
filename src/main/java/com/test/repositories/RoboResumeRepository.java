package com.test.repositories;

import com.test.models.User;
import org.springframework.data.repository.CrudRepository;

public interface RoboResumeRepository extends CrudRepository <User, Long> {

}
