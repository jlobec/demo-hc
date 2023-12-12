-- Sample data for hcp table
INSERT INTO hcp (id, name, address_link, status)
VALUES
(1, 'John Doe', 1, 'A'),
(2, 'Jane Smith', 2, 'A'),
(3, 'Michael Johnson', 3, 'I'),
(4, 'Emily Adams', 4, 'A'),
(5, 'Robert Lee', 5, 'I'),
(6, 'Sarah Brown', 6, 'A'),
(7, 'William Taylor', 7, 'A'),
(8, 'Olivia White', 8, 'I'),
(9, 'James Wilson', 9, 'A'),
(10, 'Ava Anderson', 10, 'I');

-- Sample data for hco table
INSERT INTO hco (id, name, address_link, status)
VALUES
(1, 'ABC Hospital', 11, 'A'),
(2, 'XYZ Clinic', 12, 'A'),
(3, 'City Medical Center', 13, 'I'),
(4, 'Community Health Services', 14, 'A'),
(5, 'Healthcare Solutions Inc.', 15, 'I'),
(6, 'Global Wellness Group', 16, 'A'),
(7, 'Regional Medical Associates', 17, 'A'),
(8, 'Nationwide Healthcare', 18, 'I'),
(9, 'Quality Care Network', 19, 'A'),
(10, 'MediCo Health', 20, 'I');

-- Sample data for affiliations table
INSERT INTO affiliations
(id, parent_link, child_link, status, type)
VALUES
(1, 1, 2, 'A', 'HCP_HCP'),
(2, 1, 3, 'A', 'HCP_HCP'),
(3, 2, 4, 'I', 'HCP_HCP'),
(4, 4, 5, 'A', 'HCP_HCP'),
(5, 6, 7, 'A', 'HCP_HCP'),
(6, 7, 8, 'A', 'HCP_HCP'),
(7, 1, 1, 'A', 'HCP_HCO'),
(8, 3, 6, 'I', 'HCP_HCO'),
(9, 5, 9, 'A', 'HCP_HCO'),
(10, 8, 10, 'I', 'HCP_HCO');

-- Sample data for addresses table
INSERT INTO addresses (id, parent_link, parent_type, addr1, addr2, city, state, zip, status)
VALUES
(1, 1, 'HCP', '123 Main Street', 'Suite 101', 'New York City', 'NY', '10001', 'A'),
(2, 2, 'HCP', '456 Park Avenue', NULL, 'Los Angeles', 'CA', '90001', 'A'),
(3, 3, 'HCP', '789 First Street', 'Unit B', 'Chicago', 'IL', '60601', 'I'),
(4, 4, 'HCP', '101 Second Avenue', NULL, 'Houston', 'TX', '77001', 'A'),
(5, 5, 'HCP', '555 Elm Road', 'Apt 3C', 'Miami', 'FL', '33101', 'I'),
(6, 6, 'HCP', '777 Oak Lane', NULL, 'San Francisco', 'CA', '94101', 'A'),
(7, 7, 'HCP', '999 Maple Street', 'Suite 201', 'Seattle', 'WA', '98101', 'A'),
(8, 8, 'HCP', '222 Pine Avenue', 'Unit 10', 'Boston', 'MA', '02101', 'I'),
(9, 9, 'HCP', '333 Cedar Drive', NULL, 'Denver', 'CO', '80201', 'A'),
(10, 10, 'HCP', '444 Walnut Circle', 'Apt 5B', 'Phoenix', 'AZ', '85001', 'I'),
(11, 1, 'HCO', '100 Hospital Road', NULL, 'New York City', 'NY', '10001', 'A'),
(12, 2, 'HCO', '200 Clinic Avenue', 'Suite 300', 'Los Angeles', 'CA', '90001', 'A'),
(13, 3, 'HCO', '300 Medical Center Drive', NULL, 'Chicago', 'IL', '60601', 'I'),
(14, 4, 'HCO', '400 Health Street', 'Unit A', 'Houston', 'TX', '77001', 'A'),
(15, 5, 'HCO', '500 Wellness Road', NULL, 'Miami', 'FL', '33101', 'I'),
(16, 6, 'HCO', '600 Global Avenue', 'Suite 200', 'San Francisco', 'CA', '94101', 'A'),
(17, 7, 'HCO', '700 Regional Drive', NULL, 'Seattle', 'WA', '98101', 'A'),
(18, 8, 'HCO', '800 Nationwide Lane', 'Unit 20', 'Boston', 'MA', '02101', 'I'),
(19, 9, 'HCO', '900 Care Circle', NULL, 'Denver', 'CO', '80201', 'A'),
(20, 10, 'HCO', '1000 Health Avenue', 'Apt 50C', 'Phoenix', 'AZ', '85001', 'I');