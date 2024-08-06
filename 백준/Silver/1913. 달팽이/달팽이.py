import sys
from collections import deque
input = sys.stdin.readline

def init(now, x, y, direction):

    while now:
    
        if x + dx[direction%4] == -1 or x + dx[direction%4] == n or y + dy[direction%4] == -1 or y + dy[direction%4] == n or graph[y+dy[direction%4]][x+dx[direction%4]] != 0:
            direction += 1

        nx = x + dx[direction%4]
        ny = y + dy[direction%4]

        graph[ny][nx] = now
        now -= 1
        x, y = nx, ny

def search(x, y, target):
    q = deque([(x, y)])
    v[y][x] = 1

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if -1 < nx < n and -1 < ny < n:
                if graph[ny][nx] == target:
                    return nx+1, ny+1
                
                if not v[ny][nx]:
                    v[ny][nx] = 1
                    q.append((nx, ny))

n = int(input())
graph = [[0] * n for _ in range(n)]
v = [[0] * n for _ in range(n)]

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

graph[0][0] = n*n

init(n*n-1, 0, 0, 0)

target = int(input())

ans_x, ans_y = search(0, 0, target)

for i in range(n):
    for j in range(n):
        print(graph[i][j], end=" ")
    print()
print(ans_y, ans_x)