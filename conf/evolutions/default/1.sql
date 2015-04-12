# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table oauth2_access_token (
  access_token              varchar(255) not null,
  refresh_token             varchar(255),
  user_id                   bigint,
  client_id                 varchar(255),
  scope                     varchar(255),
  expires_in                integer,
  created_at                datetime,
  constraint pk_oauth2_access_token primary key (access_token))
;

create table oauth2_auth_code (
  authorization_code        varchar(255) not null,
  redirect_uri              varchar(255),
  scope                     varchar(255),
  client_id                 varchar(255),
  expires_in                bigint,
  user_id                   bigint,
  constraint pk_oauth2_auth_code primary key (authorization_code))
;

create table oauth2_client (
  id                        varchar(255) not null,
  secret                    varchar(255),
  redirect_uri              varchar(255),
  scope                     varchar(255),
  constraint pk_oauth2_client primary key (id))
;

create table oauth2_client_grant_type (
  grant_type_id             bigint,
  client_type               varchar(20))
;

create table oauth2_grant_type (
  id                        bigint auto_increment not null,
  grant_type                varchar(20),
  user_id                   bigint,
  constraint pk_oauth2_grant_type primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table oauth2_access_token;

drop table oauth2_auth_code;

drop table oauth2_client;

drop table oauth2_client_grant_type;

drop table oauth2_grant_type;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

