package tse.java.util;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tse.java.util.qualifier.TSE2023DB;

@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    @PersistenceContext(unitName="CargaUyPersistenceUnit")
    @TSE2023DB
    private EntityManager em;

}