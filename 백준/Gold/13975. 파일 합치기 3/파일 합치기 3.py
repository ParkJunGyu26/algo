import sys
import heapq

input = sys.stdin.readline

t = int(input())

for i in range(t):
    k = int(input())
    hq = list(map(int, input().split()))
    heapq.heapify(hq)

    ans = 0
    while len(hq) > 1:
        target = heapq.heappop(hq) + heapq.heappop(hq)
        ans += target
        heapq.heappush(hq, target)
    print(ans)