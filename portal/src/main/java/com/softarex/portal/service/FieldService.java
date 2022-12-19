package com.softarex.portal.service;

import com.softarex.portal.model.Field;

import java.util.List;

public interface FieldService {
    Field save(Field field);

    List<Field> getFieldsByQuestionnaireId(Long id);

    Field update(Field field);

    void delete(Long id);

    boolean isUserOwnerOfField(String email, Long fieldId);
}
