INSERT INTO customers (customer_id, name, surname, initial_credit) VALUES (1, 'Ali', 'Sari', 0);
INSERT INTO customers (customer_id, name, surname, initial_credit) VALUES (2, 'Ani', 'Tari', 2000);
INSERT INTO customers (customer_id, name, surname, initial_credit) VALUES (3, 'Abi', 'Kari', 3000);
INSERT INTO customers (customer_id, name, surname, initial_credit) VALUES (4, 'Aki', 'Lari', 4000);
INSERT INTO customers (customer_id, name, surname, initial_credit) VALUES (5, 'Dadi', 'Jar', 5000);

INSERT INTO accounts (account_id, customer_id, account_number, available_balance) VALUES (100, 1, '1111', 100);
INSERT INTO accounts (account_id, customer_id, account_number, available_balance) VALUES (200, 1, '2222', 300);
INSERT INTO accounts (account_id, customer_id, account_number, available_balance) VALUES (300, 2, '3333', 400);
INSERT INTO accounts (account_id, customer_id, account_number, available_balance) VALUES (400, 2, '4444', 500);
INSERT INTO accounts (account_id, customer_id, account_number, available_balance) VALUES (500, 3, '5555', 700);
INSERT INTO accounts (account_id, customer_id, account_number, available_balance) VALUES (600, 3, '6666', 1000);


--INSERT INTO accounts (account_id, customerId, number) VALUES (300, 1, 3333);
--INSERT INTO accounts (account_id, customerId, number) VALUES (400, 2, 4444);
--INSERT INTO accounts (account_id, customerId, number) VALUES (500, 3, 5555);

INSERT INTO transactions (transaction_id, account_id, amount) VALUES (0, 100, 2);
