package com.softserve.academy.repository;

import com.softserve.academy.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    State findByName(String name);

//    @Query(value = "select * from states order by id", nativeQuery = true)
    @Query("select state from State state order by state.id")
    List<State> getAll();

//    List<State> findAllByOrderByIdAsc();

}
