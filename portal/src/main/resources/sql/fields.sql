create table fields
(
    id               serial primary key,
    label            varchar not null,
    type             varchar not null,
    is_required      bool    not null,
    is_active        bool    not null,
    questionnaire_id bigint  not null references questionnaires (id)
)