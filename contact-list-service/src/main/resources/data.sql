delete from person;
delete from contact;

INSERT INTO person(id, name, created_at, modified_at ) VALUES(
	1,
	'teste',
	CURRENT_TIMESTAMP(),
	CURRENT_TIMESTAMP()
);

INSERT INTO contact(id, person_id, type, value, created_at, modified_at) VALUES(
	1,
	1,
	'EMAIL',
	'teste@teste.com',
	CURRENT_TIMESTAMP(),
	CURRENT_TIMESTAMP()
);

INSERT INTO contact(id, person_id, type, value, created_at, modified_at) VALUES(
	2,
	1,
	'PHONE',
	'011 5532-5123',
	CURRENT_TIMESTAMP(),
	CURRENT_TIMESTAMP()
);
	
INSERT INTO contact(id, person_id, type, value, created_at, modified_at) VALUES(
	3,
	1,
	'WHATS_APP',
	'011 99864-5632',
	CURRENT_TIMESTAMP(),
	CURRENT_TIMESTAMP()
);