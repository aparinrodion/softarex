package com.softarex.portal.service.impl;

import com.softarex.portal.model.Response;
import com.softarex.portal.repository.ResponseRepository;
import com.softarex.portal.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {
    private final ResponseRepository responseRepository;


    @Override
    public Response save(Response response) {
        response.getFieldResponses()
                .forEach(fieldResponse -> fieldResponse.setResponse(response));
        return responseRepository.save(response);
    }

    @Override
    public List<Response> getResponses(Long questionnaireId) {
        return responseRepository.getAllByQuestionnaireId(questionnaireId);
    }
}
