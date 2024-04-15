import sys, heapq

input = sys.stdin.readline

hq = []
n = int(input())

for i in range(n):
    num = int(input())
    if num:
        heapq.heappush(hq, num)
    else:
        if hq:
            print(heapq.heappop(hq))
        else:
            print(0)