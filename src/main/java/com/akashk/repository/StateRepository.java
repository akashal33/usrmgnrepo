package com.akashk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akashk.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{
	
	@Query("select s.stateName from State s where s.country.countryName like :counntryName")
	List<String> getStates(@Param("counntryName") String counntryName);

}
