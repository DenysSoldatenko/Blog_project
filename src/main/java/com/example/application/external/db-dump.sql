-- Create categories table
CREATE TABLE categories
(
    id       serial PRIMARY KEY,
    name     varchar(20)       NOT NULL,
    articles integer DEFAULT 0 NOT NULL
);

-- Create articles table
CREATE TABLE articles
(
    id          bigserial PRIMARY KEY,
    title       varchar(25)                NOT NULL,
    "group"     varchar(25)                NOT NULL,
    logo        varchar(50)                NOT NULL,
    "desc"      text                       NOT NULL,
    content     text                       NOT NULL,
    id_category integer                    NOT NULL REFERENCES categories (id) ON UPDATE CASCADE ON DELETE RESTRICT,
    created     timestamp(0) DEFAULT now() NOT NULL,
    views       bigint       DEFAULT 0     NOT NULL,
    comments    integer      DEFAULT 0     NOT NULL
);

-- Create accounts table
CREATE TABLE accounts
(
    id      bigserial PRIMARY KEY,
    email   varchar(100)               NOT NULL UNIQUE,
    name    varchar(30)                NOT NULL,
    avatar  varchar(255),
    created timestamp(0) DEFAULT now() NOT NULL
);

-- Create comments table
CREATE TABLE comments
(
    id         bigserial PRIMARY KEY,
    id_account bigint                     NOT NULL REFERENCES accounts (id) ON UPDATE CASCADE ON DELETE RESTRICT,
    id_article bigint                     NOT NULL REFERENCES articles (id) ON UPDATE CASCADE ON DELETE RESTRICT,
    content    text                       NOT NULL,
    created    timestamp(0) DEFAULT now() NOT NULL
);