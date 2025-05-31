# 임스의 레벨 입력
L = int(input())

# 아케인 리버 퀘스트 정보
arcane_quests = [
    # (퀘스트 레벨, 1차 감소, 2차 감소)
    (200, 210, 220),
    (210, 220, 225),
    (220, 225, 230),
    (225, 230, 235),
    (230, 235, 245),
    (235, 245, 250),
]

# 그란디스 퀘스트 정보
grandis_quests = [
    (260, 265, 270),
    (265, 270, 275),
    (270, 275, 280),
    (275, 280, 285),
    (280, 285, 290),
    (285, 290, 295),
    (290, 295, 300),
]

def get_monster_count(L, quests):
    result = []
    for quest_level, dec1, dec2 in quests:
        if L < quest_level:
            result.append(0)
        elif L < dec1:
            result.append(500)
        elif L < dec2:
            result.append(300)
        else:
            result.append(100)
    return result

arcane_result = get_monster_count(L, arcane_quests)
grandis_result = get_monster_count(L, grandis_quests)

print(' '.join(map(str, arcane_result)))
print(' '.join(map(str, grandis_result)))
