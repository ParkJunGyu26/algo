-- 코드를 작성해주세요
SELECT
    dat.ID AS ID,
    (CASE
        WHEN dat.SIZE_OF_COLONY <= 100 THEN 'LOW'
        WHEN dat.SIZE_OF_COLONY <= 1000 THEN 'MEDIUM'
        ELSE 'HIGH'
    END) AS SIZE
FROM ECOLI_DATA AS dat
ORDER BY
    dat.ID ASC