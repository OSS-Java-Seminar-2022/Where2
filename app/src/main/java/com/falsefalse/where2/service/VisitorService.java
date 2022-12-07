package com.falsefalse.where2.service;

import com.falsefalse.where2.models.Visitor;
import com.falsefalse.where2.persistence.repositories.VisitorRepository;
import com.falsefalse.where2.service.mapper.VisitorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VisitorService {
    private VisitorRepository visitorRepository;

    public Visitor getVisitor() {
        // TODO: bgasperov; change get to find, and return Optional
        return VisitorMapper.INSTANCE.fromEntity(visitorRepository.getById(1));
    }
}
