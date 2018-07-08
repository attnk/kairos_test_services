INSERT INTO contact(id, phone, email, whats_app, created_at, modified_at) VALUES(
	1,
	'011 5532-5123',
	'teste@teste.com',
	'011 99864-5632',
	CURRENT_TIMESTAMP(),
	CURRENT_TIMESTAMP()
);

INSERT INTO person(id, contatc_id, name, created_at, modified_at ) VALUES(
	1,
	1,
	'teste',
	CURRENT_TIMESTAMP(),
	CURRENT_TIMESTAMP()
);