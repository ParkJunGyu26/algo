import sys
from heapq import *

input = sys.stdin.readline
INF = int(1e9)

def dijkstra(start):
    q = []
    heappush(q, (0, start))
    distance[start] = 0
    while q:
        dist, now = heappop(q)
        if distance[now] < dist:
            continue

        for b, c in graph[now]:
            cost = distance[now] + c
            if cost < distance[b]:
                distance[b] = cost
                heappush(q, (cost, b))

n = int(input())
m = int(input())
graph = [[] for i in range(n+1)]
distance = [INF] * (n+1)
for i in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))

start, end = map(int, input().split())
dijkstra(start)
print(distance[end])