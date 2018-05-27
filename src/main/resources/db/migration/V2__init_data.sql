INSERT INTO person ( id, version, name, gender ) VALUES
  (nextval('family_seq'), 0, 'King Arthur', 'MALE'),
  (nextval('family_seq'), 0, 'Qeen Margret', 'FEMALE'),
  (nextval('family_seq'), 0, 'Bill', 'MALE'),
  (nextval('family_seq'), 0, 'Flora', 'FEMALE'),
  (nextval('family_seq'), 0, 'Charlie', 'MALE'),
  (nextval('family_seq'), 0, 'Percy', 'MALE'),
  (nextval('family_seq'), 0, 'Audrey', 'FEMALE'),
  (nextval('family_seq'), 0, 'Ronald', 'MALE'),
  (nextval('family_seq'), 0, 'Helen', 'FEMALE'),
  (nextval('family_seq'), 0, 'Ginerva', 'FEMALE'),
  (nextval('family_seq'), 0, 'Harry', 'MALE'),
  (nextval('family_seq'), 0, 'Victoire', 'FEMALE'),
  (nextval('family_seq'), 0, 'Ted', 'MALE'),
  (nextval('family_seq'), 0, 'Dominique', 'FEMALE'),
  (nextval('family_seq'), 0, 'Louis', 'MALE'),
  (nextval('family_seq'), 0, 'Molly', 'FEMALE'),
  (nextval('family_seq'), 0, 'Lucy', 'FEMALE'),
  (nextval('family_seq'), 0, 'Malfoy', 'MALE'),
  (nextval('family_seq'), 0, 'Rose', 'FEMALE'),
  (nextval('family_seq'), 0, 'Hugo', 'MALE'),
  (nextval('family_seq'), 0, 'Darcy', 'FEMALE'),
  (nextval('family_seq'), 0, 'James', 'MALE'),
  (nextval('family_seq'), 0, 'Alice', 'FEMALE'),
  (nextval('family_seq'), 0, 'Albus', 'MALE'),
  (nextval('family_seq'), 0, 'Lily', 'FEMALE'),
  (nextval('family_seq'), 0, 'Remus', 'MALE'),
  (nextval('family_seq'), 0, 'Draco', 'MALE'),
  (nextval('family_seq'), 0, 'Aster', 'FEMALE'),
  (nextval('family_seq'), 0, 'William', 'MALE'),
  (nextval('family_seq'), 0, 'Ron', 'MALE'),
  (nextval('family_seq'), 0, 'Ginny', 'FEMALE');


INSERT into FAMILY_MEMBER (id, FATHER_ID, MOTHER_ID, SPOUSE_ID) values
  ((select id from person where name = 'King Arthur'), null, null, null),
  ((select id from person where name = 'Qeen Margret'), null, null, (select id from person where name = 'King Arthur')),
  ((select id from person where name = 'Bill'), (select id from person where name = 'King Arthur'),
          (select id from person where name = 'Qeen Margret'), null),
  ((select id from person where name = 'Flora'), null, null, (select id from person where name = 'Bill')),
  ((select id from person where name = 'Charlie'), (select id from person where name = 'King Arthur'),
          (select id from person where name = 'Qeen Margret'), null),
  ((select id from person where name = 'Percy'), (select id from person where name = 'King Arthur'),
          (select id from person where name = 'Qeen Margret'), null),
  ((select id from person where name = 'Audrey'), null, null, (select id from person where name = 'Percy')),
  ((select id from person where name = 'Ronald'), (select id from person where name = 'King Arthur'),
          (select id from person where name = 'Qeen Margret'), null),
  ((select id from person where name = 'Helen'), null, null, (select id from person where name = 'Ronald')),
  ((select id from person where name = 'Ginerva'), (select id from person where name = 'King Arthur'),
          (select id from person where name = 'Qeen Margret'), null),
  ((select id from person where name = 'Harry'), null, null, (select id from person where name = 'Ginerva')),
  ((select id from person where name = 'Victoire'), (select id from person where name = 'Bill'),
          (select id from person where name = 'Flora'), null),
  ((select id from person where name = 'Ted'), null, null, (select id from person where name = 'Victoire')),
  ((select id from person where name = 'Dominique'), (select id from person where name = 'Bill'),
          (select id from person where name = 'Flora'), null),
  ((select id from person where name = 'Louis'), (select id from person where name = 'Bill'),
          (select id from person where name = 'Flora'), null),
  ((select id from person where name = 'Molly'), (select id from person where name = 'Percy'),
          (select id from person where name = 'Audrey'), null),
  ((select id from person where name = 'Lucy'), (select id from person where name = 'Percy'),
          (select id from person where name = 'Audrey'), null),
  ((select id from person where name = 'Rose'), (select id from person where name = 'Ronald'),
          (select id from person where name = 'Helen'), null),
  ((select id from person where name = 'Malfoy'), null, null, (select id from person where name = 'Rose')),
  ((select id from person where name = 'Hugo'), (select id from person where name = 'Ronald'),
          (select id from person where name = 'Helen'), null),
  ((select id from person where name = 'James'), (select id from person where name = 'Harry'),
          (select id from person where name = 'Ginerva'), null),
  ((select id from person where name = 'Darcy'), null, null, (select id from person where name = 'James')),
  ((select id from person where name = 'Albus'), (select id from person where name = 'Harry'),
          (select id from person where name = 'Ginerva'), null),
  ((select id from person where name = 'Alice'), null, null, (select id from person where name = 'Albus')),
  ((select id from person where name = 'Lily'), (select id from person where name = 'Harry'),
          (select id from person where name = 'Ginerva'), null),
  ((select id from person where name = 'Remus'), (select id from person where name = 'Ted'),
          (select id from person where name = 'Victoire'), null),
  ((select id from person where name = 'Draco'), (select id from person where name = 'Malfoy'),
          (select id from person where name = 'Rose'), null),
  ((select id from person where name = 'Aster'), (select id from person where name = 'Malfoy'),
          (select id from person where name = 'Rose'), null),
  ((select id from person where name = 'William'), (select id from person where name = 'James'),
          (select id from person where name = 'Darcy'), null),
  ((select id from person where name = 'Ron'), (select id from person where name = 'Albus'),
          (select id from person where name = 'Alice'), null),
  ((select id from person where name = 'Ginny'), (select id from person where name = 'James'),
          (select id from person where name = 'Darcy'), null);

UPDATE FAMILY_MEMBER set SPOUSE_ID = (select id from person where name = 'Qeen Margret')
  where id =(select id from person where name = 'King Arthur');

INSERT into EVENT_LOG (SEQUENCE, TYPE, DATA) values
  (nextval('event_seq'), 'PersonAdded', '{"name":"King Arthur","gender":"MALE"}'),
  (nextval('event_seq'), 'SpouseAdded', '{"name":"King Arthur","spouseName":"Qeen Margret","spouseGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"King Arthur","mothersName":"Qeen Margret","childsName":"Bill","childsGender":"MALE"}'),
  (nextval('event_seq'), 'SpouseAdded', '{"name":"Bill","spouseName":"Flora","spouseGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"King Arthur","mothersName":"Qeen Margret","childsName":"Charlie","childsGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"King Arthur","mothersName":"Qeen Margret","childsName":"Percy","childsGender":"MALE"}'),
  (nextval('event_seq'), 'SpouseAdded', '{"name":"Percy","spouseName":"Audrey","spouseGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"King Arthur","mothersName":"Qeen Margret","childsName":"Ronald","childsGender":"MALE"}'),
  (nextval('event_seq'), 'SpouseAdded', '{"name":"Ronald","spouseName":"Helen","spouseGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"King Arthur","mothersName":"Qeen Margret","childsName":"Ginerva","childsGender":"FEMALE"}'),
  (nextval('event_seq'), 'SpouseAdded', '{"name":"Ginerva","spouseName":"Harry","spouseGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Bill","mothersName":"Flora","childsName":"Victoire","childsGender":"FEMALE"}'),
  (nextval('event_seq'), 'SpouseAdded', '{"name":"Victoire","spouseName":"Ted","spouseGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Bill","mothersName":"Flora","childsName":"Dominique","childsGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Bill","mothersName":"Flora","childsName":"Louis","childsGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Percy","mothersName":"Audrey","childsName":"Molly","childsGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Percy","mothersName":"Audrey","childsName":"Lucy","childsGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Ronald","mothersName":"Helen","childsName":"Rose","childsGender":"FEMALE"}'),
  (nextval('event_seq'), 'SpouseAdded', '{"name":"Rose","spouseName":"Malfoy","spouseGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Ronald","mothersName":"Helen","childsName":"Hugo","childsGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Harry","mothersName":"Ginerva","childsName":"James","childsGender":"MALE"}'),
  (nextval('event_seq'), 'SpouseAdded', '{"name":"James","spouseName":"Darcy","spouseGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Harry","mothersName":"Ginerva","childsName":"Albus","childsGender":"MALE"}'),
  (nextval('event_seq'), 'SpouseAdded', '{"name":"Albus","spouseName":"Alice","spouseGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Harry","mothersName":"Ginerva","childsName":"Lily","childsGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Ted","mothersName":"Victoire","childsName":"Remus","childsGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Malfoy","mothersName":"Rose","childsName":"Draco","childsGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Malfoy","mothersName":"Rose","childsName":"Aster","childsGender":"FEMALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"James","mothersName":"Darcy","childsName":"William","childsGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Albus","mothersName":"Alice","childsName":"Ron","childsGender":"MALE"}'),
  (nextval('event_seq'), 'ChildAdded', '{"fathersName":"Albus","mothersName":"Alice","childsName":"Ginny","childsGender":"FEMALE"}');























  
  
