package com.equitysync.processor.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.equitysync.processor.dto.EquityDTO;

@Component
public interface EquityRepository extends CrudRepository<EquityDTO, Integer>,EquityRepositoryCustom {

}
