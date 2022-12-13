package com.softarex.portal.controller;

import com.softarex.portal.dto.FieldDto;
import com.softarex.portal.dto.QuestionnaireDto;
import com.softarex.portal.mapper.FieldMapper;
import com.softarex.portal.mapper.QuestionnaireMapper;
import com.softarex.portal.model.Questionnaire;
import com.softarex.portal.security.CustomUser;
import com.softarex.portal.service.QuestionnairesService;
import com.softarex.portal.service.impl.FieldServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questionnaires")
public class QuestionnairesController {
    private final QuestionnairesService service;
    private final QuestionnaireMapper mapper;
    private final FieldServiceImpl fieldService;
    private final FieldMapper fieldMapper;

    @Secured("ROLE_USER")
    @PostMapping
    public QuestionnaireDto save(@RequestBody QuestionnaireDto questionnaireDto, Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Questionnaire questionnaire = mapper.mapToEntity(questionnaireDto);
        Questionnaire savedQuestionnaire = service.save(questionnaire, customUser.getId());
        return mapper.mapToDto(savedQuestionnaire);
    }

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    public List<FieldDto> getFields(@PathVariable Long id) {
        return fieldService.getFieldsByQuestionnaireId(id).stream()
                .map(fieldMapper::mapToDto)
                .toList();
    }

    @Secured("ROLE_USER")
    @GetMapping
    public List<QuestionnaireDto> getByAuthor(Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return service.getByAuthorId(customUser.getId()).stream()
                .map(mapper::mapToDto)
                .toList();
    }
}