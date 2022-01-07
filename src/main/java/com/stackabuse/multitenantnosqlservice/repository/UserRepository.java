package com.stackabuse.multitenantnosqlservice.repository;

import com.stackabuse.multitenantnosqlservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, String> {
}
