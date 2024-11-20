DROP TABLE IF EXISTS t_accounts;

CREATE TABLE t_accounts (
	id bigint GENERATED ALWAYS AS IDENTITY,
	user_name varchar(255) NOT NULL,
	password varchar(60) NOT NULL,
	CONSTRAINT t_account_pkey PRIMARY KEY (id),
	CONSTRAINT t_account_ukey UNIQUE (user_name)
);