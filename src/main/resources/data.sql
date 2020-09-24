DROP TABLE IF EXISTS bidding_details;
DROP TABLE IF EXISTS auction;
DROP TABLE IF EXISTS user_details;
DROP TABLE IF EXISTS item_details;






CREATE TABLE user_details (
email varchar PRIMARY KEY,
first_name varchar NOT NULL,
last_name varchar NOT NULL,
contact_number long NOT NULL
);

INSERT INTO user_details(email, first_name, last_name, contact_number) VALUES
('abc@cars24.com', 'John', 'Doe', 12345567890),
('xyz@cars24.com', 'John', 'Wick', 12345567890),
('pqr@cars24.com', 'John', 'Ray', 12345567890);



CREATE TABLE item_details(
    id int PRIMARY KEY,
    details varchar
);

INSERT INTO item_details(id, details) VALUES
(1,'This is details of car 1'),
(2, 'This is details of car 2'),
(3, 'This is details of car 3'),
(4, 'This is details of car 4'),
(5, 'This is details of car 5');


CREATE TABLE auction(
id int PRIMARY KEY,
item_id int NOT NULL, 
min_price int NOT NULL,
step_rate int NOT NULL,
highest_bid int NOT NULL,
auction_status varchar NOT NULL,
FOREIGN KEY (item_id) REFERENCES item_details(id)
);

INSERT INTO auction(id, item_id, min_price, step_rate, highest_bid, auction_status) VALUES 
(1,1, 1000, 250, 3000, 'RUNNING'),
(2, 2, 1500, 500, 5500, 'RUNNING'),
(3, 3, 800, 100, 2100, 'OVER'),
(4, 4, 10000, 1000, 17000, 'RUNNING'),
(5, 5, 5000, 200, 8000, 'OVER');

CREATE TABLE bidding_details(
id int AUTO_INCREMENT PRIMARY KEY,
auction_id int NOT NULL,
user_id varchar NOT NULL,
bid_price int NOT NULL,
bid_status varchar NOT NULL,
FOREIGN KEY (auction_id) REFERENCES auction(id),
FOREIGN KEY (user_id) REFERENCES user_details(email)
)