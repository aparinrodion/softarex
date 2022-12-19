package com.softarex.portal.service;

import com.softarex.portal.model.Response;

import java.util.List;

public interface ResponseService {
    Response save(Response response);

    List<Response> getResponses(Long questionnaireId);
}
