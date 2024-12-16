DROP TABLE IF EXISTS t_accounts_roles;
DROP TABLE IF EXISTS t_roles;
DROP TABLE IF EXISTS t_accounts;

CREATE TABLE t_accounts (
	id bigint GENERATED ALWAYS AS IDENTITY,
	user_name varchar(255) NOT NULL,
	password varchar(60) NOT NULL,
	CONSTRAINT t_account_pkey PRIMARY KEY (id),
	CONSTRAINT t_account_ukey UNIQUE (user_name)
);

CREATE TABLE t_roles (
	id bigint GENERATED ALWAYS AS IDENTITY,
	role_name varchar(50) NOT NULL,
	"default" boolean DEFAULT false,
	CONSTRAINT t_roles_pkey PRIMARY KEY (id),
	CONSTRAINT t_roles_ukey UNIQUE (role_name)
);

CREATE TABLE t_accounts_roles (
	role_id bigint NOT NULL,
	account_id bigint NOT NULL,
	CONSTRAINT t_account_role_pkey PRIMARY KEY (role_id,account_id),
	CONSTRAINT t_role_fkey FOREIGN KEY (role_id) REFERENCES t_roles(id),
	CONSTRAINT t_account_fkey FOREIGN KEY (account_id) REFERENCES t_accounts(id) ON DELETE CASCADE
);