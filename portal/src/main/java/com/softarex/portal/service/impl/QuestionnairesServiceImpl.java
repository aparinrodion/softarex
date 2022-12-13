package com.softarex.portal.service.impl;

import com.softarex.portal.model.Questionnaire;
import com.softarex.portal.repository.QuestionnairesRepository;
import com.softarex.portal.security.CustomUser;
import com.softarex.portal.service.QuestionnairesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionnairesServiceImpl implements QuestionnairesService {
    private final QuestionnairesRepository questionnairesRepository;

    @Override
    public Questionnaire save(Questionnaire questionnaire, Long authorId) {
        questionnaire.setAuthorId(authorId);
        return questionnairesRepository.save(questionnaire);
    }

    @Override
    public List<Questionnaire> getByAuthorId(Long authorId) {
        return questionnairesRepository.getAllByAuthorId(authorId);
    }
}
