-- INSERT EMPLOYEES			
insert into employee (employee_id, first_name, last_name, email) values (1, '길동', '홍', 'hong@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (2, '라니', '고', 'go@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (3, '스티븐', '킹', 'king@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (4, '날두', '호', 'ho@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (5, '펭수', '김', 'kim@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (6, '피터', '팬', 'pen@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (7, '순신', '이', 'lee@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (8, '감찬', '강', 'kang@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (9, '유신', '김', 'yousin@gmail.com');

-- INSERT PROJECTS			
insert into project (project_id, name, stage, description) values (1000, '대형 프로젝트', '시작전', '할일이 많음');
insert into project (project_id, name, stage, description) values (1001, '새 직원 인사',  '완료', '필요한 부서의 새 직원 고용');
insert into project (project_id, name, stage, description) values (1002, '오피스 리모델링', '진행중', '오래된 오피스 환경을 새것처럼 리모델링');
insert into project (project_id, name, stage, description) values (1003, '회사 보안 강화', '진행중', '출 입문 인증 지문센서 추가');

-- INSERT PROJECT_EMPLOYEE_RELATION 
insert into project_employee (employee_id, project_id) values (1,1000);
insert into project_employee (employee_id, project_id) values (1,1001);
insert into project_employee (employee_id, project_id) values (1,1002);
insert into project_employee (employee_id, project_id) values (1,1003);
insert into project_employee (employee_id, project_id) values (3,1000);
insert into project_employee (employee_id, project_id) values (5,1002);
insert into project_employee (employee_id, project_id) values (6,1001);
insert into project_employee (employee_id, project_id) values (6,1002);
insert into project_employee (employee_id, project_id) values (6,1003);
													