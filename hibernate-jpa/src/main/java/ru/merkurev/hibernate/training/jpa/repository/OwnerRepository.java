package ru.merkurev.hibernate.training.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.entity.Owner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class OwnerRepository {

    private final EntityManager em;

    public Owner findById(Long id) {
        return em.find(Owner.class, id);
    }

    public Owner save(Owner owner) {
        if (owner.getId() == null) {
            em.persist(owner);
        } else {
            em.merge(owner);
        }
        return findById(owner.getId());
    }

    public List<Owner> getOwnersAsNamedQuery() {
        return em.createNamedQuery("get_all_owners", Owner.class).getResultList();
    }

    public Owner getOwnerAsNamedQuery(Long id) {
        TypedQuery<Owner> ownerQuery = em.createNamedQuery("get_owner_by_id", Owner.class);
        return ownerQuery.setParameter("id", id).getSingleResult();
    }

    public Owner getOwnerAsNativeQuery(Long id) {
        return (Owner) em.createNativeQuery("select * from owner where id = :id", Owner.class)
                         .setParameter("id", id)
                         .getSingleResult();
    }
}
