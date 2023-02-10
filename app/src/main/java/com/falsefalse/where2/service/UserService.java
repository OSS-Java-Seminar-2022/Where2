package com.falsefalse.where2.service;

import com.falsefalse.where2.dto.RegistrationDto;
import com.falsefalse.where2.models.UserModel;
import com.falsefalse.where2.persistence.entities.UserEntity;
import com.falsefalse.where2.persistence.entities.enums.Role;
import com.falsefalse.where2.persistence.repositories.UserRepository;
import com.falsefalse.where2.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final static String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "NoSuchElementException: User with [id: %s] not found!";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserModel> getAll() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::fromEntity).toList();
    }

    public UserModel get(int id) {
        return UserMapper.INSTANCE.fromEntity(userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id))));
    }

    public UserModel get(String username) {
        return UserMapper.INSTANCE.fromEntity(userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, username))));
    }

    public UserEntity getEntity(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, username)));
    }

    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void register(RegistrationDto newUserModel) {
        var created = UserMapper.INSTANCE.toEntity(newUserModel);
        created.setRoles(List.of(Role.USER));
        created.setEvents(new ArrayList<>());
        created.setPassword(passwordEncoder.encode(newUserModel.getPassword()));
        userRepository.save(created);
    }

    public UserModel update(Integer id, UserModel newUserModel) {
        var oldUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id)));
        UserMapper.INSTANCE.updateEntity(newUserModel, oldUser);

        return UserMapper.INSTANCE.fromEntity(userRepository.save(oldUser));
    }

    public HttpStatus delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;

    }


}
