package com.bank.data.model.dao;


import com.bank.data.model.entity.AccountEntryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AccountEntryDao {

    @PersistenceContext
    EntityManager entityManager;

    public AccountEntryEntity create(AccountEntryEntity accounty) {
        entityManager.persist(accounty);
        return accounty;
    }

    public void delete(Long  id) {

        AccountEntryEntity account = entityManager.find(AccountEntryEntity.class, id);
        if (entityManager.contains(account))
            entityManager.remove(account);
        else
            entityManager.remove(entityManager.merge(account));
        return;
    }

    public AccountEntryEntity update(AccountEntryEntity accounty) {
        entityManager.merge(accounty);
        return accounty;
    }

    public AccountEntryEntity getById(long id) {
        return entityManager.find(AccountEntryEntity.class, id);
    }

    public List<AccountEntryEntity> getByContaContabil(Long contaContabil) {

        Query query = entityManager.createQuery("from AccountEntryEntity where accountNumber = :contaContabil");
        query.setParameter("contaContabil", contaContabil);

        List<AccountEntryEntity> result = query.getResultList();

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<AccountEntryEntity> getAll() {
        return entityManager.createQuery("from AccountEntryEntity").getResultList();
    }


}
