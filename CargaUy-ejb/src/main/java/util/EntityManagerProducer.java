package util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.qualifier.TSE2023DB;


@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    @PersistenceContext(unitName="myAppPersistenceUnit")
    @TSE2023DB
    private EntityManager em;

}
