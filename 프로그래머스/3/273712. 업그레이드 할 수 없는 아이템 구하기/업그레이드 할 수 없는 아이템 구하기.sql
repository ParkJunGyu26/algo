-- 코드를 작성해주세요
SELECT
    inf.ITEM_ID AS ITEM_ID,
    inf.ITEM_NAME AS ITEM_NAME,
    inf.RARITY AS RARITY
FROM
    ITEM_INFO as inf
WHERE
    inf.ITEM_ID NOT IN (
        SELECT
            DISTINCT itr.PARENT_ITEM_ID
        FROM
            ITEM_TREE as itr
        WHERE
            itr.PARENT_ITEM_ID IS NOT NULL
    )
ORDER BY
    inf.ITEM_ID DESC