package com.softarex.portal.service.impl;

import com.softarex.portal.model.Field;
import com.softarex.portal.repository.FieldRepository;
import com.softarex.portal.service.FieldResponseService;
import com.softarex.portal.service.FieldService;
import com.softarex.portal.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final OptionService optionService;
    private final FieldResponseService fieldResponseService;

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

    @Transactional
    @Override
    public Field update(Field field) {
        optionService.deleteAllByFieldId(field.getId());
        field.getOptions().forEach(option -> option.setField(field));
        return fieldRepository.save(field);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        fieldResponseService.deleteAllByFieldId(id);
        optionService.deleteAllByFieldId(id);
        fieldRepository.deleteById(id);
    }

    @Override
    public boolean isUserOwnerOfField(String email, Long fieldId) {
        return fieldRepository.existsByAuthorEmail(email, fieldId);
    }
}
