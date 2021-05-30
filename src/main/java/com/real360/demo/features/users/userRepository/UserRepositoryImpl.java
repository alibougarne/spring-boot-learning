package com.real360.demo.features.users.userRepository;

import com.real360.demo.features.roles.Role;
import com.real360.demo.features.roles.Role_;
import com.real360.demo.features.users.User;
import com.real360.demo.features.users.User_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> fetchAllUsers(Long take, Long skip) throws Exception {
//        List<User> users = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Making The Query Object From The 'CriteriaBuilder' Instance
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        SetJoin<User, Role> roles = userRoot.join(User_.roles);

        // select query
        CriteriaQuery<User> select = criteriaQuery
                .select(userRoot)
                .where(criteriaBuilder.like(roles.get(Role_.NAME), "%basic%"))
                .distinct(true);
        TypedQuery<User> typedQuery = entityManager.createQuery(select);
        List<User> usersList = typedQuery.getResultList();

        if (usersList.isEmpty())
            throw new Exception("Can't find users");
        return usersList;
    }
}
