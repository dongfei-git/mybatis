CREATE TABLE tb1_employee (
	id INT ( 11 ) PRIMARY KEY AUTO_INCREMENT,
	last_name VARCHAR ( 255 ),
	gender CHAR ( 1 ),
email VARCHAR ( 255 )
);

CREATE TABLE tb1_dept(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	dept_name VARCHAR(255)
);

ALTER TABLE tb1_employee ADD COLUMN d_id INT(11);
ALTER TABLE tb1_employee ADD CONSTRAINT fk_emp_dept FOREIGN KEY(d_id) REFERENCES tb1_dept(id);

select e.id id,e.last_name last_name,e.gender gender,e.email email,e.d_id, d_id,d.id did,d.dept_name dept_name
        from tb1_employee e,tb1_dept d
        where e.d_id=d.id and e.id=1

INSERT INTO tb1_employee(last_name,email,gender,d_id)
	VALUES('maria','maria@qq.com','F',2),('john','john@gmail.com','M',3)