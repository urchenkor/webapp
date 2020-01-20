package com.webapp.repos;

import com.webapp.domain.Subscriber;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriberRepos extends CrudRepository<Subscriber, Long> {
    List<Subscriber> findByFirstName(String firstName);
}
