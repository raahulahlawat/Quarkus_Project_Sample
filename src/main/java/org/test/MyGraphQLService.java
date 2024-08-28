package org.test;

import org.jboss.logging.Logger;
import jakarta.inject.Singleton;
import io.smallrye.mutiny.Uni;
import java.time.Instant;
import org.eclipse.microprofile.graphql.Query;

@Singleton
public class MyGraphQLService {

    private static final Logger LOGGER = Logger.getLogger(MyGraphQLService.class);

    @Query("myQuery")
    public Uni<String> myQuery(String argument) {
        LOGGER.infof("Starting myQuery with argument: %s", argument);
        Instant start = Instant.now();
        return fetchData(argument)
            .onItem().invoke(result -> {
                Instant end = Instant.now();
                long executionTime = java.time.Duration.between(start, end).toMillis();
                LOGGER.infof("Query execution time: %d ms", executionTime);
            });
    }

    private Uni<String> fetchData(String argument) {
        // Simulate data fetching
        return Uni.createFrom().item("Fetched data for " + argument);
    }
}
