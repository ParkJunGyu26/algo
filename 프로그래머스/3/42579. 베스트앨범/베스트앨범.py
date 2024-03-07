from collections import defaultdict

def solution(genres, plays):
    answer = []
    
    # 각 장르별 재생시간 저장하기 위해 defaultdict 사용
    dic = defaultdict(int)
    
    # 장르, 재생시간, 고유번호 저장 배열
    array = []
    
    for i in range(len(genres)):
        # 각 장르별 재생시간 총합
        dic[genres[i]] += plays[i]
        # [장르, 재생시간, 고유번호 저장]
        array.append([genres[i], plays[i], i])
        
    # 장르 재생시간을 기준으로 내림차순 정렬
    sdic = sorted(dic.items(), key = lambda x: x[1], reverse=True)
    
    # [장르, 재생시간, 고유번호] 정렬
    array = sorted(array, key = lambda x : (x[0], -x[1], x[2]))
    
    for i in range(len(sdic)):
        # 최대 두 개씩만 앨범에 들어갈 수 있으니 카운트
        count = 2
        for j in range(len(array)):
            if sdic[i][0] == array[j][0] and count != 0:
                answer.append(array[j][2])
                count -= 1
                
    return answer