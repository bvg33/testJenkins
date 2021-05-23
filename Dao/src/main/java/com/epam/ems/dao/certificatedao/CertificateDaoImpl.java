package com.epam.ems.dao.certificatedao;

import com.epam.ems.dao.CRDDao;
import com.epam.ems.dao.CRUDDao;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

import static com.epam.ems.dto.fields.Constant.*;


@Repository
public class CertificateDaoImpl implements CRUDDao<Certificate> {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private CRDDao<Tag> tagDao;

    @Autowired
    public CertificateDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public List<Certificate> getAll(int page, int elements) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Certificate> criteriaQuery = criteriaBuilder.createQuery(Certificate.class);
        Root<Certificate> root = criteriaQuery.from(Certificate.class);
        criteriaQuery.select(root);
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page - 1) * elements)
                .getResultList();
    }

    public Certificate getById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Certificate.class, id);
    }

    public void removeById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Certificate> criteriaDelete = criteriaBuilder.createCriteriaDelete(Certificate.class);
        Root<Certificate> root = criteriaDelete.from(Certificate.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        entityManager.getTransaction().begin();
        entityManager.createQuery(criteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void update(Certificate item) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(item);
        entityManager.getTransaction().commit();
    }

    public void insert(Certificate item) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }

    public List<Certificate> getByNamePart(String name, int page, int elements) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        String newName = "%" + name + "%";
        CriteriaQuery<Certificate> criteriaQuery = criteriaBuilder.createQuery(Certificate.class);
        Root<Certificate> root = criteriaQuery.from(Certificate.class);
        criteriaQuery.where(criteriaBuilder.like(root.get(CERTIFICATE_NAME), newName));
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page - 1) * elements)
                .getResultList();
    }

    public List<Certificate> getByTagName(String name, int page, int elements) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Certificate> criteriaQuery = criteriaBuilder.createQuery(Certificate.class);
        Root<Certificate> root = criteriaQuery.from(Certificate.class);
        Expression<Collection<Tag>> tags = root.get("tags");
        Tag tag = tagDao.getByTagName(name, 1, 5).get(0);
        Predicate containsTag = criteriaBuilder.isMember(tag, tags);
        criteriaQuery.where(containsTag);
        return entityManager
                .createQuery(criteriaQuery)
                .setFirstResult((page - 1) * elements)
                .setMaxResults(elements)
                .getResultList();
    }

    public List<Certificate> getEntitiesSortedByParameter(String sortType, String param, int page, int elements) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Certificate> criteriaQuery = criteriaBuilder.createQuery(Certificate.class);
        Root<Certificate> root = criteriaQuery.from(Certificate.class);
        if (param.equals(SORT_BY_NAME)) {
            chooseSortType(sortType, criteriaQuery, root, CERTIFICATE_NAME, criteriaBuilder);
        }
        if (param.equals(SORT_BY_DATE)) {
            chooseSortType(sortType, criteriaQuery, root, CREATE_DATE, criteriaBuilder);
        }
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page - 1) * elements)
                .getResultList();
    }

    private void chooseSortType(String sortType, CriteriaQuery<Certificate> criteriaQuery,
                                Root<Certificate> root, String field, CriteriaBuilder criteriaBuilder) {
        if (sortType.equals(DESC_SORT)) {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(field)));
        } else if (sortType.equals(ASC_SORT)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(field)));
        }
    }
}
