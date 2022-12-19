package com.softarex.portal.service.impl;

import com.softarex.portal.repository.OptionRepository;
import com.softarex.portal.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;


    @Override
    public void deleteAllByFieldId(Long fieldId) {
        optionRepository.deleteAllByFieldId(fieldId);
    }
}
