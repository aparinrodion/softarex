package com.softarex.portal.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityService {
    boolean isUserOwnerOfQuestionnaire(UserDetails userDetails, Long questionnaireId);

    boolean isUserOwnerOfField(UserDetails userDetails, Long fieldId);
}
