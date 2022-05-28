alter table if exists student_entrance_scores
    add column avg_subject double precision,
    add column min_subject integer,
    add column max_subject integer;
