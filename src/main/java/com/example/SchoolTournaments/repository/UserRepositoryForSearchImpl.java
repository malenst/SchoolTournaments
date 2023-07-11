package com.example.SchoolTournaments.repository;

import com.example.SchoolTournaments.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserRepositoryForSearchImpl implements UserRepositoryForSearch {
    private final EntityManager entityManager;

    public UserRepositoryForSearchImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UserEntity> searchByCriteria(String fragmentOfSearch, String searchParam) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> user = query.from(UserEntity.class);

        Predicate condition;
        switch (searchParam) {
            case "phone":
                condition = cb.like(user.get("phoneNumber"), "%" + fragmentOfSearch + "%");
                break;
            case "city":
                condition = cb.like(user.get("city"), "%" + fragmentOfSearch + "%");
                break;
            case "fcs":
                String[] fcsValues = fragmentOfSearch.split(" ");
                String lastName = fcsValues[0];
                String firstName = fcsValues[1];
                String patronymic = fcsValues[2];

                condition = cb.and(
                        cb.like(user.get("lastName"), "%" + lastName + "%"),
                        cb.like(user.get("firstName"), "%" + firstName + "%"),
                        cb.like(user.get("patronymic"), "%" + patronymic + "%"));
                        break;
            default:
                throw new IllegalArgumentException("Invalid search parameter: " + searchParam);
        }

        query.select(user).where(condition);

        return entityManager.createQuery(query).getResultList();
    }

}
