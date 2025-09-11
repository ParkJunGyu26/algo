-- 코드를 입력하세요
SELECT
    outs.ANIMAL_ID AS ANIMAL_ID,
    outs.NAME AS NAME
FROM
    ANIMAL_OUTS as outs
WHERE
    outs.ANIMAL_ID NOT IN (
        SELECT 
            ins.ANIMAL_ID
        FROM
            ANIMAL_INS as ins
    )
ORDER BY
    ANIMAL_ID ASC
-- outs에는 있지만,ins 에는 없는