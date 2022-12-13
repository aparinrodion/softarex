package com.softarex.portal.service.impl;

import com.softarex.portal.model.Field;
import com.softarex.portal.repository.FieldRepository;
import com.softarex.portal.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;

    @Override
    @Transactional
    public Field save(Field field) {
        field.getOptions().forEach(option -> option.setField(field));
        return fieldRepository.save(field);
    }

    @Override
    public List<Field> getFieldsByQuestionnaireId(Long id) {
        return fieldRepository.getAllByQuestionnaireId(id);
    }
}
