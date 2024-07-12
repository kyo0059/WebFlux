package com.cathay.holdings.myr.dao.persistence;

import com.cathay.holdings.myr.dao.entities.FirstTry;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstTryRepository extends R2dbcRepository<FirstTry, Long> {
}

