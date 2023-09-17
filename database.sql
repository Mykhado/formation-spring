CREATE TYPE package_status AS ENUM ('pending', 'in-transit', 'returned', 'delivered');

CREATE TABLE packages (
    id CHAR(36) PRIMARY KEY,
    number VARCHAR(256) NOT NULL,
    street VARCHAR(1024) NOT NULL,
    city VARCHAR(1024) NOT NULL,
    country VARCHAR(1024) NOT NULL,
    details VARCHAR(1024),
    phone_number VARCHAR(256) NOT NULL,
    email VARCHAR(1024) NOT NULL,
    status package_status NOT NULL,
    delivery_person_id CHAR(36) ,
    tracking_code CHAR(32) NOT NULL
);

CREATE TYPE user_role AS ENUM ('delivery-person', 'admin');

CREATE TABLE users (
    id CHAR(36) PRIMARY KEY,
    role user_role NOT NULL,
    email VARCHAR(1024) NOT NULL,
    password VARCHAR(1024) NOT NULL
);