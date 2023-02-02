package com.falsefalse.where2.service;

import com.falsefalse.where2.models.UserModel;
import com.falsefalse.where2.persistence.repositories.UserRepository;
import com.falsefalse.where2.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService {

    private final static String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "NoSuchElementException: User with [id: %s] not found!";
    private UserRepository userRepository;

    public List<UserModel> getAll() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::fromEntity).toList();
    }

    public UserModel get(int id) {
        return UserMapper.INSTANCE.fromEntity(userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id))));
    }

    public UserModel create(UserModel newUserModel) {
        var created = userRepository.save(UserMapper.INSTANCE.toEntity(newUserModel));
        return UserMapper.INSTANCE.fromEntity(created);
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
