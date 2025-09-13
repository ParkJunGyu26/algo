-- 코드를 작성해주세요
SELECT
    ed1.ID AS ID,
    IFNULL(ed2.CNT, 0) AS CHILD_COUNT
FROM
    ECOLI_DATA AS ed1
LEFT JOIN
    (
        SELECT 
            ed.PARENT_ID AS PARENT_ID,
            COUNT(*) AS CNT
        FROM
            ECOLI_DATA AS ed
        WHERE
            ed.PARENT_ID IS NOT NULL
        GROUP BY
            ed.PARENT_ID
    ) AS ed2
ON
    ed1.ID = ed2.PARENT_ID
ORDER BY
    ed1.ID ASC