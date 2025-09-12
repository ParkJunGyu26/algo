-- 코드를 입력하세요

# MEMBER_ID
# ksjs1115@gmail.com 김서준
# ksjs1115@gmail.com
# ksjs1115@gmail.com

# minjea985@naver.com 김민재
# minjea985@naver.com
# minjea985@naver.com

# soso94@naver.com 정소율
# soso94@naver.com
# soso94@naver.com

# SELECT DISTINCT rr.MEMBER_ID, mp.MEMBER_NAME
# FROM REST_REVIEW AS rr JOIN MEMBER_PROFILE AS mp ON rr.MEMBER_ID = mp.MEMBER_ID
# ORDER BY MEMBER_ID

SELECT
    mp.MEMBER_NAME AS MEMBER_NAME,
    rr.REVIEW_TEXT AS REVIEW_TEXT,
    DATE_FORMAT(rr.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM
    MEMBER_PROFILE AS mp
JOIN
    REST_REVIEW AS rr
ON
    mp.MEMBER_ID = rr.MEMBER_ID
WHERE
    mp.MEMBER_ID IN (
        SELECT
            mp.MEMBER_ID AS MEMBER_ID
        FROM
            MEMBER_PROFILE AS mp
        JOIN
            REST_REVIEW AS rr
        ON
            mp.MEMBER_ID = rr.MEMBER_ID
        GROUP BY
            mp.MEMBER_ID
        HAVING
            COUNT(*) = (
                SELECT
                    COUNT(*) AS CNT
                FROM
                    MEMBER_PROFILE AS mp
                JOIN
                    REST_REVIEW AS rr
                ON
                    mp.MEMBER_ID = rr.MEMBER_ID
                GROUP BY
                    mp.MEMBER_ID
                ORDER BY
                    COUNT(*) DESC
                LIMIT 1
            )
    )
ORDER BY
    REVIEW_DATE ASC,
    REVIEW_TEXT ASC