package com.softarex.portal.repository;

import com.softarex.portal.model.Option;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {
    @Modifying
    @Query("delete from Option o where o.field.id=:fieldId")
    void deleteAllByFieldId(@Param("fieldId") Long fieldId);
}
