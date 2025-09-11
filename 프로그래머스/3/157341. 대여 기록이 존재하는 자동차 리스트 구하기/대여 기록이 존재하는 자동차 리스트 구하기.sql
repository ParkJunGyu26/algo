-- 코드를 입력하세요
SELECT DISTINCT
    car.CAR_ID
FROM 
    CAR_RENTAL_COMPANY_CAR as car
JOIN
    CAR_RENTAL_COMPANY_RENTAL_HISTORY as history
ON
    car.CAR_ID = history.CAR_ID
WHERE
    car.CAR_TYPE = '세단' AND
    history.START_DATE LIKE '2022-10%'
ORDER BY
    car.CAR_ID DESC