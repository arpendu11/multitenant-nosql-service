package com.stackabuse.multitenantnosqlservice.config.mongo.tenant;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.stackabuse.multitenantnosqlservice.config.mongo.MongoProperties;
import com.stackabuse.multitenantnosqlservice.repository.CommonCollection;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@Configuration
@AllArgsConstructor
@EnableMongoRepositories(basePackages = {"com.stackabuse.multitenantnosqlservice.repository"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = CommonCollection.class))
public class TenantMongoConfig extends AbstractMongoClientConfiguration {

    private final MongoProperties mongoProperties;

    @Override
    protected String getDatabaseName() {
        return null;
    }

    @Bean
    public MongoClient getMongoClient() {
        MongoCredential credential = MongoCredential.createCredential(
                mongoProperties.getUsername(),
                mongoProperties.getDataBaseName(),
                mongoProperties.getPassword().toCharArray());
        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(
                                Collections.singletonList(
                                        new ServerAddress(
                                                mongoProperties.getHost(),
                                                Integer.parseInt(mongoProperties.getPort())))))
                .credential(credential)
                .build());
    }

    @Override
    @Primary
    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        return new TenantMongoDBFactory(getMongoClient(), mongoProperties.getDataBaseName());
    }

    @Override
    @Bean
    @Primary
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MappingMongoConverter converter) {
        return new MongoTemplate(mongoDbFactory, converter);
    }

    @Bean
    public MongoTemplate mongoTemplateMaster(MongoProperties mongoProperties) {
        MongoDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(
                getMongoClient(),
                mongoProperties.getDataBaseName());
        return new MongoTemplate(mongoDbFactory);
    }
}
