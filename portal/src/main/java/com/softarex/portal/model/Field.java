package com.softarex.portal.model;

import com.softarex.portal.util.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static org.hibernate.annotations.CascadeType.ALL;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "label")
    private String label;
    @Column(name = "type")
    private Type type;
    @Column(name = "is_required")
    private Boolean isRequired;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "questionnaire_id")
    private Long questionnaireId;
    @OneToMany(mappedBy = "field", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //TODO ?
    private List<Option> options;
}
