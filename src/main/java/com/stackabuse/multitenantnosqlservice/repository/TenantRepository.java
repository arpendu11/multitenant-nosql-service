package com.stackabuse.multitenantnosqlservice.repository;

import com.stackabuse.multitenantnosqlservice.entity.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@CommonCollection
public interface TenantRepository extends MongoRepository<Tenant, String> {
    Optional<Tenant> findByKey(String tenantKey);
}
