package com.epam.ems.logic.creator;

public interface Renovator<T> {
    T updateObject(T newEntity, T oldEntity);
}
