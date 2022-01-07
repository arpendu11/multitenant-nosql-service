package com.stackabuse.multitenantnosqlservice.service;

import com.stackabuse.multitenantnosqlservice.dto.CreateUserDTO;
import com.stackabuse.multitenantnosqlservice.dto.UpdateUserDTO;
import com.stackabuse.multitenantnosqlservice.entity.User;
import com.stackabuse.multitenantnosqlservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setFirstName(createUserDTO.getFirstName());
        user.setLastName(createUserDTO.getLastName());
        user.setCreatedBy("admin");
        user.setCreatedOn(System.currentTimeMillis());
        user.setLastUpdatedBy("admin");
        user.setLastUpdatedOn(System.currentTimeMillis());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(String id, UpdateUserDTO updateUserDTO) throws Exception {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new Exception("User with id: " + id + " not found in the tenant"));
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) throws Exception {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new Exception("User with id: " + id + " not found in the tenant"));
        userRepository.delete(user);
    }
}
