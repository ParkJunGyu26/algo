import sys
import heapq
input = sys.stdin.readline

n = int(input())
hq = []
for _ in range(n):
    s, e = map(int, input().split())
    heapq.heappush(hq, (s, e))

start, end = heapq.heappop(hq)
end_room = []
heapq.heappush(end_room, end)

while hq:
    start, end = heapq.heappop(hq)
    if end_room and end_room[0] <= start:
        heapq.heappop(end_room)
    heapq.heappush(end_room, end)

print(len(end_room))