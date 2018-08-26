package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * Created by Erlan Ibraev on 26.08.2018.
 */
@ImplementedBy(JPAAttachingRepository.class)
public interface AttachingRepository {

    CompletionStage<Attaching> add(Attaching attaching);

    CompletionStage<Stream<Attaching>> list();

}
