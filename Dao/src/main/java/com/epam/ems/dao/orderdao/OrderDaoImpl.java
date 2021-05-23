package com.epam.ems.dao.orderdao;

import com.epam.ems.dto.UserOrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderDaoImpl {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public OrderDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public List<UserOrderInfo> getUserCertificatesInfo(int userId, int page, int elements) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserOrderInfo> criteriaQuery = criteriaBuilder.createQuery(UserOrderInfo.class);
        Root<UserOrderInfo> root = criteriaQuery.from(UserOrderInfo.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("userId"), userId));
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page - 1) * elements)
                .getResultList();
    }

    public void addCertificateToUser(UserOrderInfo orderInfo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(orderInfo);
        entityManager.getTransaction().commit();

    }
}
