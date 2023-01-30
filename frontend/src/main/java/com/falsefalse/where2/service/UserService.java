package com.falsefalse.where2.service;

import com.falsefalse.where2.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private ApiClient client;
    private ObjectMapper mapper = new ObjectMapper();

    public List<UserModel> getAll() throws IOException, InterruptedException {
        var json = client.get("http://localhost:7070/users");
       return mapper.readValue(json, List.class);
    }
    public UserModel get(Integer id) throws IOException, InterruptedException {
        var json = client.get("http://localhost:7070/users/"+id);
        return mapper.readValue(json, UserModel.class);
    }
    public UserModel create(UserModel newUserModel) throws IOException, InterruptedException {
        var json = mapper.writeValueAsString(newUserModel);
        var response = client.post("http://localhost:7070/users", json);
        return mapper.readValue(json, UserModel.class);
    }
}
