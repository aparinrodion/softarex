package com.softarex.portal.repository;

import com.softarex.portal.model.Response;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends CrudRepository<Response, Long> {

    List<Response> getAllByQuestionnaireId(Long questionnaireId);
}
