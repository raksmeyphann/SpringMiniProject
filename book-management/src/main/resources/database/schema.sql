create table tb_book(
id serial primary key ,
title varchar ,
author varchar,
publisher varchar ,
thumbnail varchar
-- cate_id int references tb_category(id)

);
-- create table tb_category(
--   id serial primary key,
--   name varchar
-- );