-- 코드를 입력하세요

SELECT 
    CONCAT('/home/grep/src/', f.BOARD_ID, '/', f.FILE_ID, f.FILE_NAME, f.FILE_EXT) AS FILE_PATH
FROM
    USED_GOODS_FILE AS f
WHERE 
    f.BOARD_ID = (
        SELECT
            b.BOARD_ID AS BOARD_ID
        FROM
            USED_GOODS_BOARD as b
        ORDER BY
            b.VIEWS DESC
        LIMIT 1
    )
ORDER BY
    f.FILE_ID DESC