create table users(
    id_user serial primary key not null,
    firstname_user varchar (40) not null default 'not provided',
    lastname_user varchar (40) not null default 'not provided',
    nickname varchar(40) unique not null,
    email_user citext unique not null,
    password_user varchar (100) not null,
    CONSTRAINT valid_email CHECK ( email_user ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' )
);

create table category_content(
    id_category_content serial primary key not null,
    category_content varchar(100) not null unique
);

create table content(
    id_content bigserial primary key not null,
    title varchar(200) not null,
    description varchar(255) default null,
    content_url text not null unique,
    content_path text not null,
    content_name text not null,
    content_extension text not null,
    content_type text not null,
    thumbnail_url text not null,
    id_category_content integer default null references category_content(id_category_content) on delete set null,
    created_by integer default null references users(id_user) on delete set null,
    created_at TIMESTAMP default current_timestamp,
    constraint unique_content unique (content_path, content_name, content_extension)
);

create table content_rates (
    id_content_rate bigserial primary key not null,
    id_content integer not null references content (id_content) on delete cascade,
    id_user integer default null references users(id_user) on delete set null,
    rating decimal not null check (rating IN (1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0) ),
    constraint unique_rate unique (id_content, id_user)
);

-- All the following users have this passowrd: Password1!
insert into users(firstname_user, lastname_user, nickname, email_user, password_user)
values ('Amanda', 'Kaz', 'akazz', 'akaz@mail.com', '$2a$10$mxKFJUF6ke4BQG3FmZSASeTUxjDqSPHi/u39v//gOgjKnTFB7RE7W'),
       ('Rodrigo', 'Pineda', 'blure', 'blure@mail.com', '$2a$10$mxKFJUF6ke4BQG3FmZSASeTUxjDqSPHi/u39v//gOgjKnTFB7RE7W'),
       ('Arturo', 'Ronaldo', 'aronaldo', 'aronaldo@mail.com', '$2a$10$mxKFJUF6ke4BQG3FmZSASeTUxjDqSPHi/u39v//gOgjKnTFB7RE7W');

insert into category_content (category_content)
values ('Game'),
       ('Video'),
       ('Artwork'),
       ('Music');

insert into content(title, description, content_url, content_path, content_name, content_extension, content_type, thumbnail_url)
values ('MasterChief', 'MasterChief character sprite', 'files/uploads/1/masterchief.png', 'files/uploads/1/', 'masterchief', '.png', 'image/png', 'files/uploads/2/masterchief-thumbnail.png'),
       ('Yellow Bus Video', 'Sample Video for in-game story', 'files/uploads/1/yellow_bus_-_12043_(720p).mp4', 'files/uploads/1/', 'yellow_bus_-_12043_(720p)', '.mp4', 'video/mp4', 'files/uploads/1/yellow_bus_-_12043_(720p)-thumbnail.png');

insert into content_rates(id_content, id_user, rating)
values(1, 1, 1.0),
      (1, 2, 5.0),
      (1, 3, 3.0);
