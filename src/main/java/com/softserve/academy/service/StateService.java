package com.softserve.academy.service;

import com.softserve.academy.model.State;

import java.util.List;

public interface StateService {
    State create(State state);
    State readById(long id);
    State update(State state);
    void delete(long id);
    List<State> getAll();

    State getByName(String name);
}
