DELIMITER $$  
DROP PROCEDURE IF EXISTS procedureName$$
 
CREATE PROCEDURE procedureName()
BEGIN
    DECLARE i INT DEFAULT 101;
        
    WHILE i <= 200 DO
        INSERT INTO board(title , content, writer, regDate, updateDate)
          VALUES( concat('키워드',i), '키워드 데이터 에요', '길동' , now(),  now());
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER $$

CALL procedureName; 