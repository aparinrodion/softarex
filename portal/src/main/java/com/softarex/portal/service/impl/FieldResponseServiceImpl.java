package com.softarex.portal.service.impl;

import com.softarex.portal.repository.FieldResponseRepository;
import com.softarex.portal.service.FieldResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldResponseServiceImpl implements FieldResponseService {
    private final FieldResponseRepository fieldResponseRepository;

    @Override
    public void deleteAllByFieldId(Long id) {
        fieldResponseRepository.deleteAllByFieldId(id);
    }
}
