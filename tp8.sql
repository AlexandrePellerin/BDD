drop table users;
create table users (login varchar(20), mdp varchar(20), nom varchar(20), prenom varchar(20), role varchar(10), adresse varchar(50));
insert into users values('slt','bonjour','cava','oui','admin','et toi?');
insert into users values('las','lep','la','re','util','mi');
insert into users values('paul','paul','cava','oui','admin','et toi?');
insert into users values('jean','jean','la','re','util','mi');

drop table bidule;
create table bidule (libelle text);
insert into bidule values('slt');
insert into bidule values('las');

drop table numsessions;
create table numsessions (p varchar(40));
