import sys
input = sys.stdin.readline

def dfs(x, y, count):
    global ans
    v[y][x] = True

    if count == 1:
        if x == c-1 and y == 0:
            ans += 1
        return
    
    count -= 1
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if -1 < nx < c and -1 < ny < r:
            if not v[ny][nx] and g[ny][nx] == '.':
                dfs(nx, ny, count)
                v[ny][nx] = False

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
r, c, k = map(int, input().split())
g = [list(input().rstrip()) for _ in range(r)]
v = [[False] * c for _ in range(r)]

ans = 0
dfs(0, r-1, k)
print(ans)