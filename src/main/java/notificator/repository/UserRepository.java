package notificator.repository;

import notificator.model.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<UserEntity, String> {
    @Query("""
        SELECT *
        FROM "user"
        WHERE id = :id
    """)
    Mono<UserEntity> findById(String id);
}
