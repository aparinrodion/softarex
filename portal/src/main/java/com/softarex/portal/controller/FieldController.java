package com.softarex.portal.controller;

import com.softarex.portal.dto.FieldDto;
import com.softarex.portal.mapper.FieldMapper;
import com.softarex.portal.model.Field;
import com.softarex.portal.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/field")
public class FieldController {
    private final FieldService fieldService;
    private final FieldMapper fieldMapper;

    @Secured("ROLE_USER")
    @PostMapping
    public FieldDto save(@RequestBody FieldDto fieldDto) {
        Field field = fieldMapper.mapToEntity(fieldDto);
        Field savedField = fieldService.save(field);
        return fieldMapper.mapToDto(savedField);
    }

    @Secured("ROLE_USER")
    @PreAuthorize(value = "@securityServiceImpl.isUserOwnerOfField(authentication.principal, #fieldDto.id)")
    @PostMapping("/update")
    public FieldDto update(@RequestBody FieldDto fieldDto) {
        Field field = fieldMapper.mapToEntity(fieldDto);
        Field updatedField = fieldService.update(field);
        return fieldMapper.mapToDto(updatedField);
    }

    @Secured("ROLE_USER")
    @PreAuthorize(value = "@securityServiceImpl.isUserOwnerOfField(authentication.principal, #id)")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        fieldService.delete(id);
    }
}
