from heapq import heappop, heappush
import sys

input = sys.stdin.readline

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
arr.sort()
rooms = [arr[0][1]]

for i in range(1, n):
    # print("rooms1 : ", rooms)
    start, end = arr[i]
    if start >= rooms[0]:
        heappop(rooms)
    heappush(rooms, end)
    # print("rooms2 : ", rooms)

print(len(rooms))