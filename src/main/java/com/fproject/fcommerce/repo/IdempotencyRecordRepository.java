package com.fproject.fcommerce.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fproject.fcommerce.entity.IdempotencyRecord;
import com.fproject.fcommerce.entity.User;

public interface IdempotencyRecordRepository extends JpaRepository<IdempotencyRecord, Long> {
    
    Optional<IdempotencyRecord> findByUserAndIdempotencyKey(User user, String idempotencyKey);
}
