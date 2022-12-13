package com.softarex.portal.service.impl;

import com.softarex.portal.model.Option;
import com.softarex.portal.repository.OptionRepository;
import com.softarex.portal.service.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;

    @Override
    public List<Option> saveAll(List<Option> options) {
        //TODO mb remove
        return new ArrayList<>();
    }
}
