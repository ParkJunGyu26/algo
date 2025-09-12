-- 코드를 입력하세요
SELECT
    pl.ID AS ID,
    pl.NAME AS NAME,
    pl.HOST_ID AS HOST_ID
FROM
    PLACES AS pl
WHERE
    pl.HOST_ID IN
    (
        SELECT
            pl.HOST_ID
        FROM
            PLACES AS pl
        GROUP BY
            pl.HOST_ID
        HAVING
            COUNT(pl.HOST_ID) >= 2
    )
ORDER BY
    ID ASC