package com.stackabuse.multitenantnosqlservice.service;

import com.stackabuse.multitenantnosqlservice.dto.CreateUserDTO;
import com.stackabuse.multitenantnosqlservice.dto.UpdateUserDTO;
import com.stackabuse.multitenantnosqlservice.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUser(String id);

    User createUser(CreateUserDTO createUserDTO);

    User updateUser(String id, UpdateUserDTO updateUserDTO) throws Exception;

    void deleteUserById(String id) throws Exception;
}
