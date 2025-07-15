CREATE TABLE IF NOT EXISTS country (
  cid INT AUTO_INCREMENT PRIMARY KEY,
  cname VARCHAR(45) NOT NULL,
  UNIQUE KEY cid_UNIQUE (cid)
);

CREATE TABLE IF NOT EXISTS employee (
  id INT AUTO_INCREMENT PRIMARY KEY,
  createdby VARCHAR(45),
  createddtm DATETIME,
  name VARCHAR(45),
  phoneno VARCHAR(45),
  emailid VARCHAR(45),
  department VARCHAR(45),
  status VARCHAR(45),
  salary DOUBLE,
  updatedby VARCHAR(45),
  updateddtm DATETIME,
  cid INT,
  UNIQUE KEY id_UNIQUE (id),
  KEY fk_employee_country (cid),
  CONSTRAINT fk_employee_country FOREIGN KEY (cid) REFERENCES country (cid)
);

CREATE TABLE IF NOT EXISTS registration (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45),
  emailid VARCHAR(50) UNIQUE,
  password VARCHAR(45),
  mobileno VARCHAR(15),
  UNIQUE KEY id_UNIQUE (id)
);
