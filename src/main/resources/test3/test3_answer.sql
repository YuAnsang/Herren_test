-- 1. 총점(국어 + 영어 + 수학)이 높은 순으로 이름과 총점, 순위를 출력해주세요.
SELECT name, total, (@rownum := @rownum + 1) AS ranking
FROM (SELECT sum(score) AS total, name
      FROM midterm 
      GROUP BY name
      ORDER BY total DESC) a,
      (SELECT @rownum:=0) b
;

-- MySql version over or equals 8.0
SELECT name, sum(score) AS total, rank() over(order by sum(score) desc) as ranking
FROM midterm 
GROUP BY name
ORDER BY total DESC
;

-- 2. 각 과목별 최저 점수가 누구인지 이름, 과목, 점수를 출력해주세요.
SELECT a.name, b.subject, b.lowest_score
FROM midterm a
INNER JOIN (
    SELECT subject, min(score) as lowest_score
    FROM midterm
    GROUP BY subject
) b ON a.score = b.lowest_score
;

-- 3. 총점이 200 점 넘은 사람은 누구인지 이름, 총점 출력해주세요.
SELECT name, sum(score) as total
FROM midterm
GROUP BY name
HAVING total > 200
;

-- 4. 늑대의 수학점수가 잘못 입력됐습니다. 수정을 해주세요. 늑대의 수학점수는 여우와 돼지의 수학점수 평균보다 15점이 더 높습니다.
UPDATE midterm
SET score = (SELECT score FROM
             (SELECT truncate(avg(score), 0) + 15 AS score
              FROM midterm
              WHERE subject = '수학'
              AND (name = '여우' OR name = '돼지')) AS a)
WHERE name = '늑대'
AND subject = '수학'
;

COMMIT;