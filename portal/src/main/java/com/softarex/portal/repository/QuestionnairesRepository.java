package com.softarex.portal.repository;

import com.softarex.portal.model.Questionnaire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnairesRepository extends CrudRepository<Questionnaire, Long> {
    List<Questionnaire> getAllByAuthorId(Long authorId);

    @Query(nativeQuery = true, value = "select exists(select * from questionnaires q " +
            "join users u on u.id = q.author_id " +
            "where q.id=:id " +
            "and u.email=:email)")
    boolean existsByAuthorEmail(@Param("email") String email, @Param("id") Long questionnaireId);
}
