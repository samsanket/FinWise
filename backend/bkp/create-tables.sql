-- public.audit_log definition

-- Drop table

-- DROP TABLE public.audit_log;

CREATE TABLE public.audit_log (
	audit_log_id bigserial NOT NULL,
	api_name varchar(255) NULL,
	"data" text NULL,
	date_time timestamp(6) NULL,
	user_name varchar(255) NULL,
	CONSTRAINT audit_log_pkey PRIMARY KEY (audit_log_id)
);


-- public.expensecategories definition

-- Drop table

-- DROP TABLE public.expensecategories;

CREATE TABLE public.expensecategories (
	categoryid serial4 NOT NULL,
	categoryname varchar(50) NOT NULL,
	CONSTRAINT expensecategories_categoryname_key UNIQUE (categoryname),
	CONSTRAINT expensecategories_pkey PRIMARY KEY (categoryid)
);


-- public.roles definition

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
	id serial4 NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT roles_name_check CHECK (((name)::text = ANY (ARRAY[('ROLE_USER'::character varying)::text, ('ROLE_MODERATOR'::character varying)::text, ('ROLE_ADMIN'::character varying)::text]))),
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id bigserial NOT NULL,
	authenticated bool NULL,
	email varchar(50) NULL,
	"password" varchar(120) NULL,
	username varchar(20) NULL,
	CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- public.expenses definition

-- Drop table

-- DROP TABLE public.expenses;

CREATE TABLE public.expenses (
	expenseid serial4 NOT NULL,
	userid int4 NULL,
	categoryid int4 NULL,
	amount numeric(10, 2) NOT NULL,
	description text NULL,
	"date" date NOT NULL,
	CONSTRAINT expenses_pkey PRIMARY KEY (expenseid),
	CONSTRAINT expenses_categoryid_fkey FOREIGN KEY (categoryid) REFERENCES public.expensecategories(categoryid),
	CONSTRAINT expenses_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id)
);


-- public.financialgoals definition

-- Drop table

-- DROP TABLE public.financialgoals;

CREATE TABLE public.financialgoals (
	goalid serial4 NOT NULL,
	userid int4 NULL,
	goalname varchar(100) NOT NULL,
	targetamount numeric(10, 2) NOT NULL,
	targetdate date NOT NULL,
	progress numeric(5, 2) DEFAULT 0 NULL,
	CONSTRAINT financialgoals_pkey PRIMARY KEY (goalid),
	CONSTRAINT financialgoals_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id)
);


-- public.insights definition

-- Drop table

-- DROP TABLE public.insights;

CREATE TABLE public.insights (
	insightid serial4 NOT NULL,
	userid int4 NULL,
	title varchar(255) NOT NULL,
	description text NOT NULL,
	"timestamp" timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT insights_pkey PRIMARY KEY (insightid),
	CONSTRAINT insights_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id)
);


-- public.suggestions definition

-- Drop table

-- DROP TABLE public.suggestions;

CREATE TABLE public.suggestions (
	suggestionid serial4 NOT NULL,
	userid int4 NULL,
	title varchar(255) NOT NULL,
	description text NOT NULL,
	"timestamp" timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT suggestions_pkey PRIMARY KEY (suggestionid),
	CONSTRAINT suggestions_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id)
);


-- public.user_profile definition

-- Drop table

-- DROP TABLE public.user_profile;

CREATE TABLE public.user_profile (
	uid int4 NOT NULL,
	address varchar(255) NULL,
	branch varchar(255) NULL,
	college_name varchar(255) NULL,
	created_at timestamp(6) NULL,
	date_of_birth varchar(255) NULL,
	education varchar(255) NULL,
	percentage varchar(255) NULL,
	updated_at timestamp(6) NULL,
	idd int8 NULL,
	CONSTRAINT uk_enoxnjmjgspb6dot9qf0j1li0 UNIQUE (idd),
	CONSTRAINT user_profile_pkey PRIMARY KEY (uid),
	CONSTRAINT fkgrkb5guf5hnt20s8creseq17 FOREIGN KEY (idd) REFERENCES public.users(id)
);


-- public.user_roles definition

-- Drop table

-- DROP TABLE public.user_roles;

CREATE TABLE public.user_roles (
	user_id int8 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id),
	CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.userpreferences definition

-- Drop table

-- DROP TABLE public.userpreferences;

CREATE TABLE public.userpreferences (
	userid serial4 NOT NULL,
	preferredcurrency varchar(3) NULL,
	notificationpreferences varchar(100) NULL,
	CONSTRAINT userpreferences_pkey PRIMARY KEY (userid),
	CONSTRAINT userpreferences_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id)
);