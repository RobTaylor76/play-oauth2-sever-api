# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table stingray_sites (
  stingray_sites_id         bigint auto_increment not null,
  stingray_sites_name       varchar(255),
  stingray_sites_postcode   varchar(255),
  constraint pk_stingray_sites primary key (stingray_sites_id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table stingray_sites;

SET FOREIGN_KEY_CHECKS=1;

