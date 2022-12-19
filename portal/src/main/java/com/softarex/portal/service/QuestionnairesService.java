package com.softarex.portal.service;

import com.softarex.portal.model.Questionnaire;

import java.util.List;

public interface QuestionnairesService {
    Questionnaire save(Questionnaire questionnaire, Long authorId);

    List<Questionnaire> getByAuthorId(Long authorId);

    boolean isUserOwnerOfQuestionnaire(String email, Long id);
}
