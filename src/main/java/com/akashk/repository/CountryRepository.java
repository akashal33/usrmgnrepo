package com.akashk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akashk.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{

}
