package com.softarex.portal.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "questionnaires")
@EntityListeners(AuditingEntityListener.class)
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @CreatedDate
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "author_id")
    private Long authorId;
}
