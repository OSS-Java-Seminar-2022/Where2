package com.falsefalse.where2.service;

import com.falsefalse.where2.models.Visitor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VisitorService {
    private ApiClient client;

    private ObjectMapper mapper = new ObjectMapper();

    public List<Visitor> getAll() throws IOException, InterruptedException {
        var json = client.get("http://localhost:7070/visitors");
       return mapper.readValue(json, List.class);
    }

    public Visitor get(Integer id) throws IOException, InterruptedException {
        var json = client.get("http://localhost:7070/visitors/"+id);
        return mapper.readValue(json, Visitor.class);
    }

    public Visitor create(Visitor newVisitor) throws IOException, InterruptedException {
        var json = mapper.writeValueAsString(newVisitor);
        var response = client.post("http://localhost:7070/visitors", json);
        return mapper.readValue(json, Visitor.class);
    }
}
