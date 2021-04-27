
DROP TABLE if exists shoppingOrder CASCADE;
DROP TABLE if exists card CASCADE;
DROP TABLE if exists shoppingCart CASCADE;
DROP TABLE if exists wishlist CASCADE;
DROP TABLE if exists product CASCADE;
DROP TABLE if exists users CASCADE;

CREATE TABLE product(
	id 					bigint,
	name 				VARCHAR(100),
	category 			VARCHAR(100),
	description 		VARCHAR(500),
	price 				float,
	rating				float[],
	PRIMARY KEY(id));
	
CREATE TABLE users(
	id					bigint,
	firstName   		VARCHAR(100),
	lastName    		VARCHAR(100),
	emailAddress 		VARCHAR(100),
	address      		VARCHAR(100),
	username     		VARCHAR(100),
	password     		VARCHAR(100),
	shippingAddress 	VARCHAR(100),
	userType			VARCHAR(100),
	PRIMARY KEY(id));
	

CREATE TABLE shoppingorder(
	price 				float,
	registeredUserId 	bigint REFERENCES users ON DELETE CASCADE,
	productid 			bigint REFERENCES product ON DELETE CASCADE,
	timeCreated 		timestamp,
	status				VARCHAR(50),
	orderid				bigint,
	PRIMARY KEY(orderid, productid, registeredUserId));

CREATE TABLE shoppingcart(
	registeredUserId 	bigint REFERENCES users ON DELETE CASCADE,
	productid			bigint REFERENCES product ON DELETE CASCADE,
	quantity			int,
	PRIMARY KEY(registeredUserId, productid));
	
CREATE TABLE wishlist(
	registeredUserId 	bigint REFERENCES users ON DELETE CASCADE,
	productid			bigint REFERENCES product ON DELETE CASCADE,
	PRIMARY KEY(registeredUserId, productid));
	
CREATE TABLE lock(
	lockableid	bigint,
	ownerid		VARCHAR(100),
	PRIMARY KEY(lockableid, ownerid));
	
INSERT INTO product VALUES(10, 'Naked Honey Palette', 'Makeup', 'A buzzed-about eyeshadow palette with 12 golden neutrals for creating a swarm of looks', 49.00, ARRAY[5, 5, 4, 1]);
INSERT INTO product VALUES(11, 'Water Stain Lip Stain', 'Makeup', 'A water-enriched, lightweight stain that comfortably drenches lips in brilliant, high-shine color that feels fresh and non-sticky', 37.00, ARRAY[5, 5, 4, 2, 2, 1]);
INSERT INTO product VALUES(12, 'Squalane + Marine Algae Eye Cream', 'Skincare', 'A hydrating eye cream with Marine Algae Complex that reduces the appearance of fine lines and wrinkles in as few as seven days.', 54.00, ARRAY[5, 5, 5, 4, 3, 2]);

INSERT INTO users VALUES(11, 'Kate', 'Smith', 'katesmith@gmail.com', '123 km road', 'ksmith', '123km', '123 km road','class domain.RegisteredUser');
INSERT INTO users VALUES(1, 'John', 'Smith', 'Johnsmith@gmail.com', '123 km road', 'jsmith', '123jm', '','class domain.Administrator');





