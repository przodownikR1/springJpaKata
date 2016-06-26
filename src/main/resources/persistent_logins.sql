create table if not exists PERSISTENT_LOGINS ( 
  username varchar_ignorecase(100) not null, 
  series varchar(64) primary key, 
  token varchar(64) not null, 
  last_used timestamp not null
);