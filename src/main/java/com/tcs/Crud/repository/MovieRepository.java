package com.tcs.Crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.Crud.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

}
