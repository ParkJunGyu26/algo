-- 코드를 입력하세요
SELECT
    ins.ANIMAL_ID as ANIMAL_ID,
    ins.NAME as NAME
FROM
    ANIMAL_INS as ins
JOIN
    ANIMAL_OUTS as outs
ON
    ins.ANIMAL_ID = outs.ANIMAL_ID
WHERE
    ins.DATETIME > outs.DATETIME
ORDER BY
    ins.DATETIME ASC