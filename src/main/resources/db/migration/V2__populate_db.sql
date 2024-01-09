INSERT INTO worker VALUES 
	(1,'John','1995-05-08','Trainee',1000),
	(2,'William','1983-02-08','Trainee',1010),
	(3,'James','1975-05-02','Junior',1150),
	(4,'Christopher','1990-05-09','Junior',1100),
	(5,'Daniel','1997-06-08','Middle',1400),
	(6,'Liam','1985-07-08','Middle',1560),
	(7,'Lucas','1995-05-09','Middle',2000),
	(8,'Mark','1984-01-02','Middle',2500),
	(9,'Michael','1970-12-12','Senior',3000),
	(10,'Noah','1980-11-08','Senior',4500);
INSERT INTO client VALUES
	(1,'Steven'),
	(2,'Alexander'),
	(3,'Kenneth'),
	(4,'Richard'),
	(5,'Thomas');
INSERT INTO project 
	(ID,CLIENT_ID,FINISH_DATE)
VALUES
	(1,1,'2024-01-05'),
	(2,1,'2024-08-06'),
	(3,2,'2024-06-07'),
	(4,2,'2024-02-05'),
	(5,2,'2024-03-05'),
	(6,3,'2024-04-10'),
	(7,4,'2024-10-10'),
	(8,3,'2024-09-08'),
	(9,4,'2024-01-09'),
	(10,5,'2024-09-02');
INSERT INTO project_worker VALUES
	(1,2),
	(2,3),
	(3,1),
	(4,5),
	(5,4),
	(6,7),
	(7,10),
	(8,6),
	(9,8),
	(10,9);