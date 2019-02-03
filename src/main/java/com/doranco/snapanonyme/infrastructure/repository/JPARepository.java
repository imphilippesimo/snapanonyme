package com.doranco.snapanonyme.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@org.springframework.stereotype.Repository
public class JPARepository {

    @PersistenceContext
    protected EntityManager entityManager;

    //TODO we may need transaction manager
}
