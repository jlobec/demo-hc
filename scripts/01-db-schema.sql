CREATE TABLE hcp (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address_link INT,
    status VARCHAR(10) NOT NULL
);

CREATE TABLE hco (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address_link INT,
    status VARCHAR(10) NOT NULL
);

CREATE TABLE affiliations (
    id INT PRIMARY KEY,
    parent_link INT,
    child_link INT,
    status VARCHAR(10) NOT NULL,
    type VARCHAR(20) NOT NULL
);

CREATE TABLE addresses (
    id INT PRIMARY KEY,
    parent_link INT,
    parent_type VARCHAR(5) NOT NULL,
    addr1 VARCHAR(255) NOT NULL,
    addr2 VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    zip VARCHAR(20) NOT NULL,
    status VARCHAR(10) NOT NULL
);