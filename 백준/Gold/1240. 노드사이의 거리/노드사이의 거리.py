import sys
from collections import deque
input = sys.stdin.readline

def bfs(start, target):
    q = deque()
    q.append(start)
    visited[start] = True

    while q:
        node = q.popleft()
        for end, dist in graph[node]:
            if not visited[end]:
                distance[end] = distance[node] + dist
                q.append(end)
                visited[end] = True
                if end == target:
                    return 0

n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]
for i in range(n-1):
    node1, node2, dist = map(int, input().split())
    graph[node1].append((node2, dist))
    graph[node2].append((node1, dist))

for i in range(m):
    visited = [False] * (n+1)
    node1, node2 = map(int, input().split())
    distance = [0] * (n+1)
    bfs(node1, node2)
    print(distance[node2])