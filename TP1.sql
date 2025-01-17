drop table Terrain;
drop table reservation;
drop table abonne;

Create Table abonne
       (ano Integer,
       nom Char(15),
       prenom Char(15),
       dateAbo date,
       parrain Integer,
       constraint pk_abonne Primary Key(ano),
       constraint fk_abonne Foreign Key(ano) References abonne(ano));

Create Table Terrain
       (tno Integer,
       type char(1),
       numero Integer,
       constraint pk_terrain Primary Key(tno));

Create Table reservation
       (rno Integer,
       dateR TimeStamp,
       ano1 Integer,
       ano2 Integer,
       tno Integer,
       constraint pk_reservation Primary Key(rno),
       constraint fk_terrain1 Foreign Key(ano1) References abonne(ano),
       constraint fk_terrain2 Foreign Key(ano2) References abonne(ano));

insert into abonne values (1,'Durand','Adrien','1995-07-21');
insert into abonne values (2,'Pont','Jean Jacques','2017-02-15');
insert into abonne values (3,'Dupond','Marcel','2015-09-10');
insert into abonne values (4,'Smith','Will','2016-02-01');
insert into abonne values (5,'Smith','John','2017-10-30');
insert into abonne values (6,'Walker','Paul','2016-05-25');
insert into abonne values (7,'Johnson','Dwayne','2017-04-16',6);
insert into abonne values (8,'Willis','Bruce','2016-09-19');
insert into abonne values (9,'Sparow','Jack','2016-10-23');
insert into abonne values (10,'Diesel','Vin','2017-03-06');

insert into Terrain values (1,'T',1);
insert into Terrain values (2,'T',2);
insert into Terrain values (3,'T',3);
insert into Terrain values (4,'T',4);
insert into Terrain values (5,'B',1);
insert into Terrain values (6,'B',2);
insert into Terrain values (7,'B',3);
insert into Terrain values (8,'B',4);
insert into Terrain values (9,'S',1);
insert into Terrain values (10,'S',2);

insert into reservation values (1,'2016-09-14 11:00:00',8,7,1);
insert into reservation values (2,'2016-09-14 14:00:00',9,10,5);
insert into reservation values (3,'2016-09-14 16:00:00',2,5,9);
insert into reservation values (4,'2016-09-15 15:00:00',2,8,6);
insert into reservation values (5,'2016-09-15 14:00:00',5,10,3);


