INSERT INTO aut_env(id, aut, environment)
VALUES
    (11, 'ATS', 'PROD'),
    (12, 'ATS', 'PREPROD'),
    (13, 'ATS', 'TEST'),
    (14, 'ATS', 'DYNAMIC'),
    (21, 'SP', 'PROD'),
    (22, 'SP', 'PREPROD'),
    (23, 'SP', 'TEST'),
    (24, 'SP', 'DYNAMIC'),
    (31, 'VMS', 'PROD'),
    (32, 'VMS', 'PREPROD'),
    (33, 'VMS', 'TEST'),
    (34, 'VMS', 'DYNAMIC');
INSERT INTO tested_case(id, name, create_date)
VALUES
    (30718, 'Login via sso', sysdate),
    (30719, 'Logout', sysdate),
    (30731, 'Login Normal', sysdate),
    (30732, 'Login Normal Bad Creds', sysdate),
    (30720, 'Search for consultants by keywords', sysdate),
    (30721, 'Search for consultants in a given location', sysdate),
    (30733, 'Try creating empty JR, check error messages', sysdate),
    (30734, 'Create Job Requests', sysdate),
    (30735, 'Create Job Requests - shortest route', sysdate),
    (30736, 'Check Job Request creator title', sysdate),
    (30737, 'Verify Add New Client Contact', sysdate),
    (30740, 'Verify Framework Agreement Button', sysdate),
    (30742, 'Verify Hiring Manager Dropdown', sysdate),
    (30743, 'Check Description Input', sysdate),
    (31063, 'ZUS case', sysdate),
    (35860, 'Create a new client', sysdate),
    (35861, 'Edit client info', sysdate),
    (36563, 'Create new public profile', sysdate),
    (36564, 'Create News', sysdate),
    (36565, 'Link free client to free public profile', sysdate),
    (36566, 'Deactivate public profile', sysdate);
INSERT INTO aut_env_tested_case(AUT_ENV_ID, TESTED_CASE_ID)
VALUES
    (11, 30733),
    (11, 30718),
    (11, 30719),
    (11, 30731),
    (11, 30732),
    (12, 30718),
    (12, 30719),
    (12, 30731),
    (12, 30732),
    (12, 30733),
    (12, 30734),
    (12, 30735),
    (12, 30736),
    (12, 30737),
    (12, 30740),
    (12, 30742),
    (12, 30743),
    (12, 31063),
    (12, 35860),
    (12, 35861),
    (12, 36563),
    (12, 36564),
    (12, 36565),
    (12, 36566),
    (13, 30718),
    (13, 30719),
    (13, 30731),
    (13, 30732),
    (13, 30720),
    (13, 30721),
    (13, 30733),
    (13, 30734),
    (13, 30735),
    (13, 30736),
    (13, 30737),
    (13, 30740),
    (13, 30742),
    (13, 30743),
    (13, 35860),
    (13, 35861),
    (13, 36563),
    (13, 36564),
    (13, 36565),
    (13, 36566);
