package com.stackabuse.multitenantnosqlservice.config.mongo.master;

import com.stackabuse.multitenantnosqlservice.repository.CommonCollection;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@AllArgsConstructor
@EnableMongoRepositories(basePackages = {"com.stackabuse.multitenantnosqlservice.repository"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = CommonCollection.class),
        mongoTemplateRef = "mongoTemplateMaster"
)
public class MasterMongoConfig {

}
