import sys
from heapq import heappop, heappush

input = sys.stdin.readline

n = int(input())
li = []
for _ in range(n):
    n, s, e = map(int, input().split())
    li.append((s, e))
li.sort(key=lambda x:x[0])

room = 1
hq = [li[0][1]]

# print("li : ", li)
# print("len : ", len(li))

for i in range(1, n):
    # print("i : ", i)
    # print("hq1  : ", hq)
    start, end = li[i]
    if hq[0] <= start:
        heappop(hq)
    heappush(hq, end)
    # print("hq2 : ", hq)
    # print("-----")

print(len(hq))