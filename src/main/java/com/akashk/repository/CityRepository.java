package com.akashk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akashk.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	
	@Query(" select c.cityName from City c where c.state.stateName like :stateName")
	public List<String> getCities(@Param("stateName") String stateName);

	
	
}
