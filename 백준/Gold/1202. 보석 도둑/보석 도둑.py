import sys
import heapq

input = sys.stdin.readline

def solution():
    n, k = map(int, input().split())
    
    jewel = []
    for _ in range(n):
        weight, value = map(int, input().split())
        jewel.append((weight, value))
    
    bag = []
    for _ in range(k):
        bag.append(int(input()))
    
    # 보석을 무게 기준으로 정렬
    jewel.sort()
    # 가방을 무게 기준으로 정렬
    bag.sort()
    
    max_value = 0
    potential_jewels = []
    j = 0
    
    for b in bag:
        # 현재 가방에 들어갈 수 있는 보석들을 모두 추가
        while j < n and jewel[j][0] <= b:
            heapq.heappush(potential_jewels, -jewel[j][1])
            j += 1
        
        # 가장 비싼 보석을 선택
        if potential_jewels:
            max_value += -heapq.heappop(potential_jewels)
    
    print(max_value)

solution()