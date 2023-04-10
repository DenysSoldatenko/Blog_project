CREATE TABLE categories
(
    id       integer           NOT NULL,
    name     varchar(20)       NOT NULL,
    url      varchar(20)       NOT NULL,
    articles integer DEFAULT 0 NOT NULL
);

CREATE TABLE articles
(
    id          bigint                                       NOT NULL,
    title       varchar(255)                                 NOT NULL,
    url         varchar(255)                                 NOT NULL,
    logo        varchar(255)                                 NOT NULL,
    "desc"      varchar(255)                                 NOT NULL,
    content     text                                         NOT NULL,
    id_category integer                                      NOT NULL,
    created     timestamp(0) without time zone DEFAULT now() NOT NULL,
    views       bigint                         DEFAULT 0     NOT NULL,
    comments    integer                        DEFAULT 0     NOT NULL
);

CREATE TABLE accounts
(
    id      bigint                                       NOT NULL,
    email   varchar(100)                                 NOT NULL,
    name    varchar(30)                                  NOT NULL,
    avatar  varchar(255),
    created timestamp(0) without time zone DEFAULT now() NOT NULL
);

CREATE TABLE comments
(
    id         bigint                                       NOT NULL,
    id_account bigint                                       NOT NULL,
    id_article bigint                                       NOT NULL,
    content    text                                         NOT NULL,
    created    timestamp(0) without time zone DEFAULT now() NOT NULL
);

CREATE SEQUENCE account_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE SEQUENCE comment_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE ONLY categories
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);

ALTER TABLE ONLY categories
    ADD CONSTRAINT category_url_key UNIQUE (url);

ALTER TABLE ONLY articles
    ADD CONSTRAINT article_pkey PRIMARY KEY (id);

ALTER TABLE ONLY accounts
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);

ALTER TABLE ONLY accounts
    ADD CONSTRAINT account_email_key UNIQUE (email);

ALTER TABLE ONLY comments
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);

ALTER TABLE ONLY articles
    ADD CONSTRAINT article_fk FOREIGN KEY (id_category) REFERENCES categories (id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY comments
    ADD CONSTRAINT comment_fk FOREIGN KEY (id_account) REFERENCES accounts (id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY comments
    ADD CONSTRAINT comment_fk1 FOREIGN KEY (id_article) REFERENCES articles (id) ON UPDATE CASCADE ON DELETE RESTRICT;


CREATE INDEX comment_idx ON comments USING btree (id_article);
CREATE INDEX article_idx ON articles USING btree (id_category);
CREATE INDEX comment_idx1 ON comments USING btree (id_account);

SELECT pg_catalog.setval('account_seq', 1, false);
SELECT pg_catalog.setval('comment_seq', 1, false);
COMMENT ON SCHEMA public IS 'standard public schema';