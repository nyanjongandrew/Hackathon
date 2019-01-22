INSERT INTO `tbl_charges` (`id`, `max`, `min`, `reg_date`, `sendtoregistered`, `sendtounregistred`, `withdrawcharge`) VALUES
(1, 1000, 0, '2019-01-18 00:00:00', 0, 0, 0),
(2, 10000, 1001, '2019-01-18 00:00:00', 87, 205, 112),
(3, 30000, 10001, NULL, 102, 288, 180),
(4, 50000, 30001, NULL, 105, NULL, 270),
(5, 70000, 50001, NULL, 105, NULL, 300);

-- --------------------------------------------------------

INSERT INTO `tbl_customer_details` (`id_number`, `first_name`, `phone_number`, `reg_date`, `second_name`, `status`, `surname`) VALUES
(28203281, 'Chausiku', '0715404991', '2019-01-18 17:48:32', 'Fiona', 1, 'Nyanjong');

INSERT INTO `tbl_mobile_wallet` (`uuid`, `account_bal`, `firstname`, `last_activity`, `phone_number`, `pin`, `reg_date`, `secondname`, `status`, `surname`) VALUES
('ed8286e5-c97c-4808-8e95-dde426e73d40', 49795, 'Steve', 'Debit', '0715462274', '1234', '2019-01-18 17:45:18', 'Ndemi', 1, 'Maina');