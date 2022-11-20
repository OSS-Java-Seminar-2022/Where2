package com.falsefalse.where2.persistence.repositories;

import com.falsefalse.where2.persistence.entities.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<VisitorEntity, Integer> {
}
