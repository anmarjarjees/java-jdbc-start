CREATE DATABASE jdbc_test;

CREATE TABLE authors (
  author_id int(11) NOT NULL,
  first_name varchar(40) NOT NULL,
  last_name varchar(40) NOT NULL,
  email varchar(70) DEFAULT NULL,
  phone varchar(20) DEFAULT NULL,
  city varchar(30) DEFAULT NULL,
  province varchar(30) DEFAULT 'ON',
  join_date datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `authors`
--

INSERT INTO authors (author_id, first_name, last_name, email, phone, city, province, join_date) VALUES
(1, 'Sam', 'Simpson', 'samsimpson@storyclub.ca', '4161231234', 'Toronto', 'ON', '2023-06-20 17:26:55'),
(2, 'Martin', 'Smith', 'alexchow@storyclub.ca', '4161231235', 'Scarborough', 'ON', '2023-06-20 17:26:55'),
(3, 'Sara', 'Grays', 'saragrayson@ggmmaaiill.ca', '4161231236', 'Mississauga', 'ON', '2023-06-20 17:26:55'),
(4, 'Kate', 'Wilson', 'katewilson@storyclub.ca', '4161231237', 'Brampton', 'ON', '2023-06-20 17:26:55'),
(5, 'Susan', 'Clark', 'susanclarck@storyclub.ca', '4161231238', 'Toronto', 'ON', '2023-06-20 17:26:55');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (author_id),
  ADD UNIQUE KEY email (email);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authors`
--
ALTER TABLE `authors`
  MODIFY author_id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;