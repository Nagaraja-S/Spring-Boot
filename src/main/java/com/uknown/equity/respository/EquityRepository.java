package com.uknown.equity.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.uknown.equity.dto.EquityDTO;

@Component
public interface EquityRepository extends CrudRepository<EquityDTO, Integer>,EquityRepositoryCustom {

}
