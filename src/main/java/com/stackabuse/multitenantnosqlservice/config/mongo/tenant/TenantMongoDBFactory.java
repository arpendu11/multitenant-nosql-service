package com.stackabuse.multitenantnosqlservice.config.mongo.tenant;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.stackabuse.multitenantnosqlservice.util.TenantContext;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.Objects;

public class TenantMongoDBFactory extends SimpleMongoClientDatabaseFactory {

    private final String masterDB;

    public TenantMongoDBFactory(MongoClient mongoClient, String masterDB) {
        super(mongoClient, masterDB);
        this.masterDB = masterDB;
    }

    @Override
    public MongoDatabase getMongoDatabase() {
        return getMongoClient().getDatabase(getTenantDatabase());
    }

    protected String getTenantDatabase() {
        String tenantId = TenantContext.getTenantId();
        if (Objects.nonNull(tenantId)) {
            return tenantId;
        } else {
            return masterDB;
        }
    }
}
