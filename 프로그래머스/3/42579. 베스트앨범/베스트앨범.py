from collections import defaultdict

def solution(genres, plays):
    answer = []
    
    # 장르별 재생 횟수
    genres_order = defaultdict(int)
                               
    # 장르별 각 고유번호 및 재생횟수
    genres_plays = defaultdict(list)
    
    for i, (genre, v) in enumerate(zip(genres, plays)):
        genres_order[genre] += v
        genres_plays[genre].append((i, v))
                               
    for genre, _ in sorted(genres_order.items(), key = lambda x: x[1], reverse = True):
        for i, v in sorted(genres_plays[genre], key = lambda x: x[1], reverse = True)[:2]:
            answer.append(i)
            
    return answer