package com.softarex.portal.controller;

import com.softarex.portal.dto.FieldDto;
import com.softarex.portal.mapper.FieldMapper;
import com.softarex.portal.model.Field;
import com.softarex.portal.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/field")
public class FieldController {
    private final FieldService fieldService;
    private final FieldMapper fieldMapper;

    //TODO check if user is owner of questionnaire
    @PostMapping
    public FieldDto save(@RequestBody FieldDto fieldDto) {
        Field field = fieldMapper.mapToEntity(fieldDto);
        Field savedField = fieldService.save(field);
        return fieldMapper.mapToDto(savedField);
    }


}
