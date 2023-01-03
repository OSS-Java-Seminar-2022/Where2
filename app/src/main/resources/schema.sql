CREATE SEQUENCE hibernate_sequence
    START WITH 4
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS visitor(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS event(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    event_type INTEGER,
    banner VARCHAR(255),
    event_date DATE,
    starting_time TIME,
    ending_time TIME,
    price FLOAT,
    currency INTEGER,
    is_recurring BIT,
    venue INTEGER
);

CREATE TABLE IF NOT EXISTS venue(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    venue_type INTEGER,
    banner VARCHAR(255),
    owner INTEGER,
    opens_at TIME,
    closes_at TIME,
    address INTEGER
);

CREATE TABLE IF NOT EXISTS notification(
    id INTEGER PRIMARY KEY NOT NULL,
    title VARCHAR(255),
    notification_type INTEGER,
    description VARCHAR(255),
    category INTEGER,
    venue INTEGER,
    event INTEGER
);

CREATE TABLE IF NOT EXISTS image(
    id INTEGER PRIMARY KEY NOT NULL,
    url VARCHAR(255),
    venue INTEGER,
    event INTEGER
);

CREATE TABLE IF NOT EXISTS address(
    id INTEGER PRIMARY KEY NOT NULL,
    street VARCHAR(255),
    number INTEGER,
    zip_code INTEGER,
    city VARCHAR(255),
    state VARCHAR(255),
    latitude DECIMAL,
    longitude DECIMAL
);

CREATE TABLE IF NOT EXISTS visitor_event_type(
    id INTEGER PRIMARY KEY NOT NULL,
    visitor INTEGER,
    event_type INTEGER
);

CREATE TABLE IF NOT EXISTS visitor_venue_type(
    id INTEGER PRIMARY KEY NOT NULL,
    visitor INTEGER,
    venue_type INTEGER
);

CREATE TABLE IF NOT EXISTS visitor_event(
    id INTEGER PRIMARY KEY NOT NULL,
    visitor INTEGER,
    event INTEGER
);

CREATE TABLE IF NOT EXISTS visitor_notification(
    id INTEGER PRIMARY KEY NOT NULL,
    visitor INTEGER,
    notification INTEGER
);

CREATE TABLE IF NOT EXISTS event_type(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS currency(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    symbol CHAR(3)
);

CREATE TABLE IF NOT EXISTS venue_type(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS notification_type(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS notification_category(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);