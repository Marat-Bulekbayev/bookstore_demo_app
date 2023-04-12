insert into PRODUCTS (product_id, name, description, author, price, image_path) values (default, 'DawnMist Fluoride', 'DIEP flap, free', 'Timmy Yukhnev', 63.62, 'Suite 94');
insert into PRODUCTS (product_id, name, description, author, price, image_path) values (default, 'Lactovit Men Roll-On Antiperspirant Deodorant', 'Periph nerve dx proc NEC', 'Melli Fynes', 29.4, 'Room 460');
insert into PRODUCTS (product_id, name, description, author, price, image_path) values (default, 'Benzoyl peroxide emollient', 'Parasitology-lower resp', 'Ruperta Helliwell', 54.72, 'Suite 100');
insert into PRODUCTS (product_id, name, description, author, price, image_path) values (default, 'Potassium Chloride', 'Bone graft NOS', 'Jodie Fitzroy', 55.3, '8th Floor');
insert into PRODUCTS (product_id, name, description, author, price, image_path) values (default, 'Detrol LA', 'Marrow diagnost proc NEC', 'Wilbur Coveny', 60.62, 'Apt 728');

insert into USERS (user_id, name, role, email, phone, address, login, password) values (default, 'John Smith', 'ADMIN', 'jsmith@google.com', 5551234, 'Nameless street 1', 'admin', 'admin');
insert into USERS (user_id, name, role, email, phone, address, login, password) values (default, 'Alex Swift', 'MANAGER', 'aswift@google.com', 5551235, 'Nameless street 2', 'manager', 'manager');
insert into USERS (user_id, name, role, email, phone, address, login, password) values (default, 'Elle Manning', 'CUSTOMER', 'emanning@google.com', 5551236, 'Nameless street 3', 'customer', 'customer');

insert into BOOKINGS (booking_id, product_id, user_id, delivery_address, date, time, status, quantity)
values (default, (SELECT product_id FROM PRODUCTS WHERE name = 'DawnMist Fluoride'), (SELECT user_id FROM USERS WHERE name = 'Elle Manning'), 'Nameless delivery street, 1', current_date, current_time, 'SUBMITTED', 1);
insert into BOOKINGS (booking_id, product_id, user_id, delivery_address, date, time, status, quantity)
values (default, (SELECT product_id FROM PRODUCTS WHERE name = 'Benzoyl peroxide emollient'), (SELECT user_id FROM USERS WHERE name = 'Elle Manning'), 'Nameless delivery street, 2', current_date, current_time, 'REJECTED', 1);
insert into BOOKINGS (booking_id, product_id, user_id, delivery_address, date, time, status, quantity)
values (default, (SELECT product_id FROM PRODUCTS WHERE name = 'Potassium Chloride'), (SELECT user_id FROM USERS WHERE name = 'Elle Manning'), 'Nameless delivery street, 3', current_date, current_time, 'IN_DELIVERY', 1);