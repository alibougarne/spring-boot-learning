package com.real360.demo.features.users.userRepository;

import com.real360.demo.features.roles.Role;
import com.real360.demo.features.roles.Role_;
import com.real360.demo.features.users.User;
import com.real360.demo.features.users.User_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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
    public List<User> fetchAllUsers(Long skip, Long take) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Making The Query Object From The 'CriteriaBuilder' Instance
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        SetJoin<User, Role> roles = userRoot.join(User_.roles);

        // select query
        CriteriaQuery<User> select = criteriaQuery
                .select(userRoot)
                .where(criteriaBuilder.like(roles.get(Role_.NAME), "%Admin%"))
                .distinct(true);
        TypedQuery<User> typedQuery = entityManager.createQuery(select);
        // pagination
        if (take != null && skip != null) {
            typedQuery.setFirstResult(skip.intValue());
            typedQuery.setMaxResults(take.intValue());
        }
        List<User> usersList = typedQuery.getResultList();


        if (usersList.isEmpty())
            throw new Exception("Users not found");
        return usersList;
    }
}
