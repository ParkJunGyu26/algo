import sys
from collections import deque
input = sys.stdin.readline

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def bfs(start_x, start_y):
    global max_dist, password
    q = deque([(start_x, start_y, 0)])  # (x, y, distance)
    visited = [[False] * column for _ in range(row)]
    visited[start_y][start_x] = True
    
    while q:
        x, y, dist = q.popleft()
        
        # Update max_dist and password if necessary
        if dist > max_dist or (dist == max_dist and graph[start_y][start_x] + graph[y][x] > password):
            max_dist = dist
            password = graph[start_y][start_x] + graph[y][x]
        
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < column and 0 <= ny < row and graph[ny][nx] != 0 and not visited[ny][nx]:
                visited[ny][nx] = True
                q.append((nx, ny, dist + 1))

row, column = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(row)]

max_dist = 0
password = 0

for i in range(row):
    for j in range(column):
        if graph[i][j] != 0:
            bfs(j, i)

print(password)