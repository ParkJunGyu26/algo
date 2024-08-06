import sys
from collections import deque
import heapq
input = sys.stdin.readline

n = int(input())
hq = []
for i in range(n):
    s, t = map(int, input().split())
    heapq.heappush(hq, [s, t])

ans = 0
remain = [] # 끝나는 시간 저장
while hq:
    start, end = heapq.heappop(hq)
    if remain:
        remain_fast = heapq.heappop(remain)
        if remain_fast <= start:
           ans -= 1
        else:
            heapq.heappush(remain, remain_fast)
           
    heapq.heappush(remain, end)
    ans += 1

print(ans)