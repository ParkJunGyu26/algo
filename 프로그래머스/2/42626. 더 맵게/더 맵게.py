from heapq import heappop, heappush, heapify

def solution(scoville, K):
    heapify(scoville)
    answer = 0
    while scoville[0] < K and len(scoville) >= 2:
        answer += 1
        first = heappop(scoville)
        second = heappop(scoville)
        heappush(scoville, first+(second*2))
    
    if scoville[0] < K:
        answer = -1
    
    return answer