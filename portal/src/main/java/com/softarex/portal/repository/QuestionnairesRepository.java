package com.softarex.portal.repository;

import com.softarex.portal.model.Questionnaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnairesRepository extends CrudRepository<Questionnaire, Long> {
    List<Questionnaire> getAllByAuthorId(Long authorId);
}
