package com.softarex.portal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Questionnaire questionnaire;
    @OneToMany(mappedBy = "response", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FieldResponse> fieldResponses;
}
