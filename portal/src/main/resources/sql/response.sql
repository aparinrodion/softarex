create table response
(
    id               serial primary key not null,
    questionnaire_id int                not null references questionnaires (id)
)