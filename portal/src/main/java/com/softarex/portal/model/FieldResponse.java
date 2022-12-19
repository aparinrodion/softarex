package com.softarex.portal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "field_response")
public class FieldResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "field_id")
    private Long fieldId;
    @ManyToOne
    private Response response;
    @Column(name = "answer")
    private String answer;
}
