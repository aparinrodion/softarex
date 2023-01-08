package com.softarex.portal.repository;

import com.softarex.portal.model.Field;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends CrudRepository<Field, Long> {
    List<Field> getAllByQuestionnaireId(Long id);

    @Query(nativeQuery = true, value = "select exists(select * from fields f " +
            "join questionnaires q on q.id = f.questionnaire_id " +
            "join users u on u.id = q.author_id " +
            "where f.id=:id " +
            "and u.email=:email)")
    boolean existsByAuthorEmail(@Param("email") String email, @Param("id") Long fieldId);
}
