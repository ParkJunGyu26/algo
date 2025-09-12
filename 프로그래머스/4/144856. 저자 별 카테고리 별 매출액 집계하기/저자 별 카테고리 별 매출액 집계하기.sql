-- 코드를 입력하세요

SELECT
    a.AUTHOR_ID AS AUTHOR_ID,
    a.AUTHOR_NAME AS AUTHOR_NAME,
    bs_with_b.CATEGORY AS CATEGORY,
    bs_with_b.TOTAL_SALES AS TOTAL_SALES
FROM
    AUTHOR as a
JOIN
    (
    SELECT
        b.AUTHOR_ID AS AUTHOR_ID,
        b.CATEGORY AS CATEGORY,
        SUM(bs.SALES * b.PRICE) AS TOTAL_SALES
    FROM
        BOOK AS b
    JOIN
        BOOK_SALES AS bs
    ON
        b.BOOK_ID = bs.BOOK_ID
    WHERE
        bs.SALES_DATE LIKE '2022-01-%'
    GROUP BY
        b.AUTHOR_ID, b.CATEGORY
    ) AS bs_with_b
ON
    bs_with_b.AUTHOR_ID = a.AUTHOR_ID
ORDER BY
    AUTHOR_ID ASC,
    CATEGORY DESC