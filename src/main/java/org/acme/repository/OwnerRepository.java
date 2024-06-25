package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import org.acme.model.Owner;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class OwnerRepository implements PanacheRepository<Owner> {
   
    public List<Owner> findByLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) {
            return Owner.find("LOWER(lastName) LIKE LOWER(?1) ",
                Sort.by("firstName"), "%" + lastName + "%").list();
        } else {
            return Owner.listAll();
        }        
    }
}
