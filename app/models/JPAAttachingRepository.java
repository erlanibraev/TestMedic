package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by Erlan Ibraev on 26.08.2018.
 */
public class JPAAttachingRepository implements AttachingRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public JPAAttachingRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Attaching> add(Attaching attaching) {
        return supplyAsync(() -> wrap(em -> insert(em, attaching)), executionContext);
    }

    @Override
    public CompletionStage<Attaching> update(Attaching attaching) {
        return supplyAsync(() -> wrap(em -> update(em, attaching)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Attaching>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Attaching update(EntityManager em, Attaching attaching) {
        Attaching result = null;
        Attaching old = em.find(Attaching.class, attaching.id);
        if(old != null) {
            result = old;
            result.status = attaching.status;
            result.processed = new Date(new java.util.Date().getTime());
            em.persist(result);
        }
        return result;
    }

    private Attaching insert(EntityManager em, Attaching attaching) {
        em.persist(attaching);
        return attaching;
    }

    private Stream<Attaching> list(EntityManager em) {
        List<Attaching> attachings = em.createQuery("select a from Attaching  a", Attaching.class).getResultList();
        return attachings.stream();
    }
}
