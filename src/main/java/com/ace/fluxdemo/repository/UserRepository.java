package com.ace.fluxdemo.repository;

import com.ace.fluxdemo.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends ReactiveCrudRepository<User,Long> {


}
