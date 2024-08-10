import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    q = deque([(x, y)])
    visited[y][x] = 1

    while q:
        x, y = q.popleft()
        now_num = graph[y][x]

        for i in range(2):
            nx = x + dx[i]*now_num
            ny = y + dy[i]*now_num

            if -1 < nx < n and -1 < ny < n:
                if nx == n-1 and ny == n-1:
                    return True
                
                if not visited[ny][nx]:
                    visited[ny][nx] = 1
                    q.append((nx, ny))

    return False

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [[0] * n for _ in range(n)]

dx = [1, 0]
dy = [0, 1]

check = bfs(0, 0)

if not check:
    print("Hing")
else:
    print("HaruHaru")