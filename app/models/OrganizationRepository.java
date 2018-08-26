package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * Created by Erlan Ibraev on 26.08.2018.
 */
@ImplementedBy(JPAOrganizationRepository.class)
public interface OrganizationRepository {

    CompletionStage<Organization> add(Organization organization);

    CompletionStage<Stream<Organization>> list();
}
