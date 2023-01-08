package com.softarex.portal.repository;

import com.softarex.portal.model.FieldResponse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldResponseRepository extends CrudRepository<FieldResponse, Long> {
    @Modifying
    @Query("delete from FieldResponse fr where fr.fieldId=:fieldId")
    void deleteAllByFieldId(@Param("fieldId") Long fieldId);
}
