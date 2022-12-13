package com.softarex.portal.repository;

import com.softarex.portal.model.User;
import org.mapstruct.control.MappingControl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getByEmail(String email);
}
