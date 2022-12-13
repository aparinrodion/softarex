package com.softarex.portal.service;

import com.softarex.portal.model.Option;
import lombok.RequiredArgsConstructor;

import java.util.List;

public interface OptionService {
    List<Option> saveAll(List<Option> options);
}
