package com.softarex.portal.repository;

import com.softarex.portal.model.Field;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends CrudRepository<Field, Long> {
    List<Field> getAllByQuestionnaireId(Long id);
}
