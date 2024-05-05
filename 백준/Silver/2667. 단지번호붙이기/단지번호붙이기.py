from collections import deque

def bfs(a, b):
    n = len(graph)
    q = deque()
    q.append((a, b))
    graph[a][b] = 0
    count = 1

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue
            if graph[nx][ny] == 1:
                graph[nx][ny] = 0
                q.append((nx, ny))
                count += 1
    
    return count

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

n = int(input())
graph = []
for i in range(n):
    graph.append(list(map(int, input())))

cnt = []
for i in range(n):
    for j in range(n):
        if graph[i][j] == 1:
            cnt.append(bfs(i, j))

cnt.sort()
print(len(cnt))
for i in range(len(cnt)):
    print(cnt[i])