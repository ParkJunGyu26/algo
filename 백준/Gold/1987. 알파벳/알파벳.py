import sys
input = sys.stdin.readline

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def dfs(x, y):
    global ans
    ans = max(ans, len(hubo))

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if -1 < nx < c and -1 < ny < r:
            if graph[ny][nx] not in hubo:
                hubo.add(graph[ny][nx])
                dfs(nx, ny)
                hubo.remove(graph[ny][nx])

r, c = map(int, input().split())
graph = [list(input().rstrip()) for _ in range(r)]

ans = 1
hubo = set(graph[0][0])
dfs(0, 0)
print(ans)