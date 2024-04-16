import sys
from heapq import heappop, heappush

input = sys.stdin.readline
hq = []

x = int(input())
for i in range(x):
    n = int(input())
    if n == 0:
        if len(hq) == 0:
            print(0)
        else:
            print(-heappop(hq))
    else:
        heappush(hq, -n)