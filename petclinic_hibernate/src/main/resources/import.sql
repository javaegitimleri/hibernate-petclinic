INSERT INTO t_city(id,name) VALUES (1,'Ankara');
INSERT INTO t_city(id,name) VALUES (2,'Ýstanbul');
INSERT INTO t_city(id,name) VALUES (3,'Ýzmir');

INSERT INTO t_clinic(id,name) VALUES (1,'Harezmi Pet Clinic');

INSERT INTO t_pet_type (id,name) VALUES (1,  'kedi');
INSERT INTO t_pet_type (id,name) VALUES (2,  'köpek');
INSERT INTO t_pet_type (id,name) VALUES (3,  'balýk');
INSERT INTO t_pet_type (id,name) VALUES (4,  'yýlan');
INSERT INTO t_pet_type (id,name) VALUES (5,  'kuþ');
INSERT INTO t_pet_type (id,name) VALUES (6,  'kaplumbaða');
INSERT INTO t_pet_type (id,name) VALUES (7,  'at');
INSERT INTO t_pet_type (id,name) VALUES (8,  'koyun');
INSERT INTO t_pet_type (id,name) VALUES (9,  'timsah');
INSERT INTO t_pet_type (id,name) VALUES (10,  'tavuk');

INSERT INTO t_specialty (id,name) VALUES (1,  'radyoloji');
INSERT INTO t_specialty (id,name) VALUES (2,  'cerrahi');
INSERT INTO t_specialty (id,name) VALUES (3,  'diþçilik');
INSERT INTO t_specialty (id,name) VALUES (4,  'anestezi');
INSERT INTO t_specialty (id,name) VALUES (5,  'doðum');

INSERT INTO t_person (id,first_name,last_name) VALUES (1,  'Jale', 'Cengiz');
INSERT INTO t_person (id,first_name,last_name) VALUES (2,  'Hale', 'Lal');
INSERT INTO t_person (id,first_name,last_name) VALUES (3,  'Leyla', 'Denge');
INSERT INTO t_person (id,first_name,last_name) VALUES (4,  'Rafi', 'Orta');
INSERT INTO t_person (id,first_name,last_name) VALUES (5,  'Hasan', 'Sarý');
INSERT INTO t_person (id,first_name,last_name) VALUES (6,  'Sedat', 'Kuzu');

INSERT INTO t_person (id,first_name,last_name) VALUES (7,  'Ziya', 'Ferit');
INSERT INTO t_person (id,first_name,last_name) VALUES (8,  'Beþir', 'Dal');
INSERT INTO t_person (id,first_name,last_name) VALUES (9,  'Eda', 'Rize');
INSERT INTO t_person (id,first_name,last_name) VALUES (10,  'Hadi', 'Duru');
INSERT INTO t_person (id,first_name,last_name) VALUES (11,  'Pýnar', 'Mus');
INSERT INTO t_person (id,first_name,last_name) VALUES (12,  'Çiðdem', 'Su');
INSERT INTO t_person (id,first_name,last_name) VALUES (13,  'Aslý', 'Zor');
INSERT INTO t_person (id,first_name,last_name) VALUES (14,  'Murat', 'Eski');
INSERT INTO t_person (id,first_name,last_name) VALUES (15,  'Davut', 'Saz',);
INSERT INTO t_person (id,first_name,last_name) VALUES (16,  'Kadir', 'Mutlu',);

INSERT INTO t_person_email (person_id,email) VALUES (1,'jale.cengiz@gmail.com');
INSERT INTO t_person_email (person_id,email) VALUES (2,'lal@hotmail.com');
INSERT INTO t_person_email (person_id,email) VALUES (3,'leyla@gmail.com');
INSERT INTO t_person_email (person_id,email) VALUES (4,'rorta@yahoo.com');
INSERT INTO t_person_email (person_id,email) VALUES (5,'hsari@yahoo.com');
INSERT INTO t_person_email (person_id,email) VALUES (6,'kuzu@gmail.com');
INSERT INTO t_person_email (person_id,email) VALUES (7,'ziya_ferit@hotmail.com');
INSERT INTO t_person_email (person_id,email) VALUES (8,'bdal@msn.com');
INSERT INTO t_person_email (person_id,email) VALUES (9,'erize@yahoo.com');
INSERT INTO t_person_email (person_id,email) VALUES (10,'hadi@gmail.com');
INSERT INTO t_person_email (person_id,email) VALUES (11,'pmus@gmail.com');
INSERT INTO t_person_email (person_id,email) VALUES (12,'su@yahoo.com');
INSERT INTO t_person_email (person_id,email) VALUES (13,'asli.zor@gmail.com');
INSERT INTO t_person_email (person_id,email) VALUES (14,'eskim@yahoo.com');
INSERT INTO t_person_email (person_id,email) VALUES (15,'dsaz@msn.com');
INSERT INTO t_person_email (person_id,email) VALUES (16,'kadir.mutlu@gmail.com');

INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,1);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,2);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,3);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,4);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,5);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,6);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,7);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,8);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,9);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,10);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,11);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,12);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,13);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,14);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,15);
INSERT INTO t_clinic_person (clinic_id,person_id) VALUES (1,16);

INSERT INTO t_vet (id,works_full_time) VALUES (1,true);
INSERT INTO t_vet (id,works_full_time) VALUES (2,true);
INSERT INTO t_vet (id,works_full_time) VALUES (3,true);
INSERT INTO t_vet (id,works_full_time) VALUES (4,true);
INSERT INTO t_vet (id,works_full_time) VALUES (5,false);
INSERT INTO t_vet (id,works_full_time) VALUES (6,false);

INSERT INTO t_owner (id,rating) VALUES (7,100);
INSERT INTO t_owner (id,rating) VALUES (8,100);
INSERT INTO t_owner (id,rating) VALUES (9,200);
INSERT INTO t_owner (id,rating) VALUES (10,200);
INSERT INTO t_owner (id,rating) VALUES (11,200);
INSERT INTO t_owner (id,rating) VALUES (12,100);
INSERT INTO t_owner (id,rating) VALUES (13,200);
INSERT INTO t_owner (id,rating) VALUES (14,100);
INSERT INTO t_owner (id,rating) VALUES (15,100);
INSERT INTO t_owner (id,rating) VALUES (16,200);

INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (7, 1, '1. Sokak Keçiören', '5325551023','HOME');
INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (8, 1, '2. Sokak Çankaya', '5325551749','HOME');
INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (9, 1, '3. Sokak Çankaya', '5555558763','WORK');
INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (10, 2, '4. Sokak Acýbadem', '5335553198','HOME');
INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (11, 2, '5. Sokak Kartal', '5445552765','WORK');
INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (12, 2, '6. Sokak Beyoðlu', '5055552654','WORK');
INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (13, 2, '7. Sokak Sarýyer', '5555555387','WORK');
INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (14, 3,'8. Sokak Konak', '5425557683','HOME');
INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (15, 3, '9. Sokak Altýnordu', '5325559435','HOME');
INSERT INTO t_address (owner_id,city_id,street,phone,phone_type) VALUES (16, 3, '10. Sokak Karþýyaka', '5335555487','WORK');

INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (1, 1);
INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (1, 2);
INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (1, 3);
INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (2, 1);
INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (2, 2);
INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (3, 2);
INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (4, 3);
INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (5, 2);
INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (5, 3);
INSERT INTO t_vet_specialty (vet_id,specialty_id) VALUES (5, 4);

INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (1,  'Maviþ', '2005-09-07', 1, 7);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (2,  'Donetello', '2008-08-06', 6, 8);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (3,  'Karabaþ', '2001-04-17', 2, 9);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (4,  'Joe', '2009-03-07', 2, 9);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (5,  'Canavar', '2010-11-30', 3, 10);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (6,  'Tatlým', '2010-01-20', 4, 11);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (7,  'Samanta', '2008-09-04', 1, 12);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (8,  'Boncuk', '2008-09-04', 1, 12);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (9,  'Þanslý', '2007-08-06', 5, 13);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (10,  'Karaburun', '2009-02-24', 2, 13);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (11,  'Twetty', '2000-03-09', 5, 15);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (12,  'Tarçýn', '2000-06-24', 2, 16);
INSERT INTO t_pet (id,pet_name,birth_date,type_id,owner_id) VALUES (13,  'Sarý', '2002-06-08', 1, 16);

INSERT INTO t_visit (id,pet_id,visit_order,visit_date,visit_description) VALUES (1,  7, 0, '2013-03-04', 'yaralanma');
INSERT INTO t_visit (id,pet_id,visit_order,visit_date,visit_description) VALUES (2,  7, 1, '2011-03-04', 'hastalýk');
INSERT INTO t_visit (id,pet_id,visit_order,visit_date,visit_description) VALUES (3,  8, 0, '2010-06-04', 'kontrol');
INSERT INTO t_visit (id,pet_id,visit_order,visit_date,visit_description) VALUES (4,  8, 1, '2012-09-04', 'kýsýrlaþtýrma');
INSERT INTO t_visit (id,pet_id,visit_order,visit_date,visit_description) VALUES (5,  8, 2, '2013-10-14', 'aþý');
INSERT INTO t_visit (id,pet_id,visit_order,visit_date,visit_description) VALUES (6,  8, 3, '2014-11-04', 'kontrol');
INSERT INTO t_visit (id,pet_id,visit_order,visit_date,visit_description) VALUES (7,  9, 0, '2015-11-22', 'yaralanma');
INSERT INTO t_visit (id,pet_id,visit_order,visit_date,visit_description) VALUES (8,  10, 0, '2015-11-22', 'yaralanma');

INSERT INTO t_image_content (id,content) VALUES (1,rawtohex('image 1 content'));
INSERT INTO t_image_content (id,content) VALUES (2,rawtohex('image 2 content'));
INSERT INTO t_image_content (id,content) VALUES (3,rawtohex('image 3 content'));
INSERT INTO t_image_content (id,content) VALUES (4,rawtohex('image 4 content'));
INSERT INTO t_image_content (id,content) VALUES (5,rawtohex('image 5 content'));

INSERT INTO t_image (id,image_content_id,pet_id,file_path,width,height) VALUES (1,1,1,'c:/mavis1.png',300,600);
INSERT INTO t_image (id,image_content_id,pet_id,file_path,width,height) VALUES (2,2,1,'d:/users/mavis2.png',500,700);
INSERT INTO t_image (id,image_content_id,pet_id,file_path,width,height) VALUES (3,3,3,'/user/home/kopek.png',400,500);
INSERT INTO t_image (id,image_content_id,pet_id,file_path,width,height) VALUES (4,4,3,'kopek2.jpeg',500,200);
INSERT INTO t_image (id,image_content_id,pet_id,file_path,width,height) VALUES (5,5,3,'c:/users/kenan/kopek3.gif',100,300);


ALTER SEQUENCE hibernate_sequence RESTART WITH 100;