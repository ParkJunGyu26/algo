SELECT
    fi.ID AS ID,
    fni.FISH_NAME AS FISH_NAME,
    fi.LENGTH AS LENGTH
FROM
    FISH_INFO AS fi
JOIN
    FISH_NAME_INFO AS fni ON fi.FISH_TYPE = fni.FISH_TYPE
JOIN (
    SELECT
        FISH_TYPE,
        MAX(LENGTH) AS MAX_LENGTH
    FROM
        FISH_INFO
    GROUP BY
        FISH_TYPE
) AS max_fish_info ON fi.FISH_TYPE = max_fish_info.FISH_TYPE AND fi.LENGTH = max_fish_info.MAX_LENGTH
ORDER BY
    fi.ID ASC;