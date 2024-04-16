from heapq import heappop, heappush
import sys
input = sys.stdin.readline

hq = []     # 양수
ans = []    # 음수

x = int(input())
for i in range(x):
    num = int(input())
    if num != 0:
        heappush(hq, (abs(num), num))
        continue
    if not hq:
        print(0)
    else:
        print(heappop(hq)[1])