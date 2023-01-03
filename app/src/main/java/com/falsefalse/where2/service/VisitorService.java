package com.falsefalse.where2.service;

import com.falsefalse.where2.models.Visitor;
import com.falsefalse.where2.persistence.repositories.VisitorRepository;
import com.falsefalse.where2.service.mapper.VisitorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class VisitorService {

    private final static String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "NoSuchElementException: Visitor with [id: %s] not found!";
    private VisitorRepository visitorRepository;

    public List<Visitor> getVisitors() {
        return visitorRepository.findAll().stream().map(VisitorMapper.INSTANCE::fromEntity).toList();
    }

    public Visitor getVisitor(int id) {
        return VisitorMapper.INSTANCE.fromEntity(visitorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id))));
    }

    public Visitor createVisitor(Visitor newVisitor) {
        var created = visitorRepository.save(VisitorMapper.INSTANCE.toEntity(newVisitor));
        return VisitorMapper.INSTANCE.fromEntity(created);
    }

    public Visitor putVisitor(Integer id, Visitor newVisitor) {
        var oldVisitor = visitorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id)));
        VisitorMapper.INSTANCE.updateEntity(newVisitor, oldVisitor);

        return VisitorMapper.INSTANCE.fromEntity(visitorRepository.save(oldVisitor));
    }

    public HttpStatus deleteVisitor(Integer id) {
        if (visitorRepository.existsById(id)) {
            visitorRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;

    }


}
