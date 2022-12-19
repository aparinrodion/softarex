package com.softarex.portal.service.impl;

import com.softarex.portal.service.FieldService;
import com.softarex.portal.service.QuestionnairesService;
import com.softarex.portal.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final QuestionnairesService questionnairesService;
    private final FieldService fieldService;

    @Override
    public boolean isUserOwnerOfQuestionnaire(UserDetails userDetails, Long questionnaireId) {
        return questionnairesService.isUserOwnerOfQuestionnaire(userDetails.getUsername(), questionnaireId);
    }

    @Override
    public boolean isUserOwnerOfField(UserDetails userDetails, Long fieldId) {
        return fieldService.isUserOwnerOfField(userDetails.getUsername(), fieldId);
    }
}
