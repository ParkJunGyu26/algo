import sys, pprint
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    q = deque()
    q.append((x, y))
    visited[y][x] = True

    while q:
        x, y = q.popleft()

        for j in range(8):
            nx = x + dx[j]
            ny = y + dy[j]

            if -1 < nx < i and -1 < ny < i:
                if not visited[ny][nx] and not graph[ny][nx]:
                    visited[ny][nx] = True
                    q.append((nx, ny))
                    graph[ny][nx] = graph[y][x] + 1

dx = [1, 2, 2, 1, -1, -2, -2, -1]
dy = [2, 1, -1, -2, -2, -1, 1, 2]

t = int(input())
for _ in range(t):
    i = int(input())
    graph = [[0] * i for __ in range(i)]
    visited = [[False] * i for __ in range(i)]
    a, b = map(int, input().split())
    c, d = map(int, input().split())
    bfs(b, a)
    # pprint.pp(graph)
    print(graph[c][d])