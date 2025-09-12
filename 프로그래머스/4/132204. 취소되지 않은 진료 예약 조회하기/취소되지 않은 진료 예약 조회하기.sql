-- 코드를 입력하세요

# SELECT *
# FROM APPOINTMENT

SELECT
    dt_with_ap.APNT_NO AS APNT_NO,
    pt.PT_NAME AS PT_NAME,
    pt.PT_NO AS PT_NO,
    dt_with_ap.MCDP_CD AS MCDP_CD,
    dt_with_ap.DR_NAME AS DR_NAME,
    dt_with_ap.APNT_YMD AS APNT_YMD
FROM
    PATIENT as pt
JOIN
    (
    SELECT
        ap.PT_NO AS PT_NO,
        ap.APNT_NO AS APNT_NO,
        ap.MCDP_CD AS MCDP_CD,
        dt.DR_NAME AS DR_NAME,
        ap.APNT_YMD AS APNT_YMD
    FROM 
        DOCTOR AS dt
    JOIN
        APPOINTMENT as ap
    ON
        dt.DR_ID = MDDR_ID
    WHERE
        ap.MCDP_CD = 'CS' AND
        ap.APNT_YMD LIKE '2022-04-13%' AND
        ap.APNT_CNCL_YN = 'N'
        ) as dt_with_ap
ON
    pt.PT_NO = dt_with_ap.PT_NO
ORDER BY
    APNT_YMD ASC