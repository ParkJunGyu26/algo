import sys
import heapq
from collections import defaultdict
input = sys.stdin.readline

Q = int(input())
dd = defaultdict(list)
ans = 0

for _ in range(Q):
    info = list(input().rstrip().split())
    query = info[0]
    name = info[1]

    if query == '1':
        for num in info[3:]:
            heapq.heappush(dd[name], -int(num))

    elif query == '2':
        cnt = int(info[2])
        if name not in dd.keys():
            continue

        for num in range(min(len(dd[name]), cnt)):
            ans += -heapq.heappop(dd[name])

print(ans)