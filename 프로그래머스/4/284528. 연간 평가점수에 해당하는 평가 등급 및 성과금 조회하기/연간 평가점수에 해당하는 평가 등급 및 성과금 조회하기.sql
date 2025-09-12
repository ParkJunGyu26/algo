# SCORE	EMP_NO
# 88	2017002
# 89	2018001
# 87.5	2019001
# 90.5	2020002
# 86.5	2020005

SELECT
    hg.EMP_NO AS EMP_NO,
    he.EMP_NAME AS EMP_NAME,
    (CASE
        WHEN (hg.SCORE >= 96) THEN 'S'
        WHEN (hg.SCORE >= 90) THEN 'A'
        WHEN (hg.SCORE >= 80) THEN 'B'
        ELSE 'C'
    END) AS GRADE,
    (CASE
        WHEN (hg.SCORE >= 96) THEN he.SAL * 0.2
        WHEN (hg.SCORE >= 90) THEN he.SAL * 0.15
        WHEN (hg.SCORE >= 80) THEN he.SAL * 0.1
        ELSE he.SAL * 0
    END) AS BONUS
FROM
    HR_EMPLOYEES AS he
JOIN
    (
        SELECT
            AVG(hg.SCORE) AS SCORE,
            hg.EMP_NO AS EMP_NO
        FROM 
            HR_GRADE AS hg
        GROUP BY 
            hg.EMP_NO
    ) AS hg
ON
    he.EMP_NO = hg.EMP_NO
ORDER BY
    EMP_NO ASC

# SELECT
#     he.EMP_NO AS EMP_NO,
#     he.EMP_NAME AS EMP_NAME,
#     (CASE
#         WHEN (AVG(hg.SCORE) >= 96) THEN 'S'
#         WHEN (AVG(hg.SCORE) >= 90) THEN 'A'
#         WHEN (AVG(hg.SCORE) >= 80) THEN 'B'
#         ELSE 'C'
#     END) AS GRADE,
#     (CASE
#         WHEN (AVG(hg.SCORE) >= 96) THEN he.SAL + (he.SAL * 0.2)
#         WHEN (AVG(hg.SCORE) >= 90) THEN he.SAL + (he.SAL * 0.15)
#         WHEN (AVG(hg.SCORE) >= 80) THEN he.SAL + (he.SAL * 0.1)
#         ELSE he.SAL
#     END) AS BONUS
# FROM
#     HR_GRADE AS hg
# JOIN
#     HR_EMPLOYEES AS he
# ON
#     hg.EMP_NO = he.EMP_NO
# GROUP BY
#     EMP_NO
# ORDER BY
#     EMP_NO ASC