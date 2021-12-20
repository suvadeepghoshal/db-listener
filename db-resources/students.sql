-- Table: public.students

-- DROP TABLE public.students;

CREATE TABLE public.students
(
    roll_num integer NOT NULL,
    f_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    l_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_on timestamp DEFAULT CURRENT_TIMESTAMP without time zone NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (roll_num),
    CONSTRAINT students_email_key UNIQUE (email)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.students
    OWNER to postgres;
