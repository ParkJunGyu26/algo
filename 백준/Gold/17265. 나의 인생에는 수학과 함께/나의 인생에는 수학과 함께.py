import sys
from collections import deque
input = sys.stdin.readline

def dfs(x, y):

    if len(num) == n:
        target = int(num[0])
        # print("cal : ", cal)
        # print("num : ", num)
        for i in range(1, n):
            if cal[i-1] == '+':
                target += int(num[i])
            elif cal[i-1] == '-':
                target -= int(num[i])
            elif cal[i-1] == '*':
                target *= int(num[i])
        
        ans.append(target)
        return 
    
    for i in range(2):
        nx = x + dx[i]
        ny = y + dy[i]

        if -1 < nx < n and -1 < ny < n:
            if not v[ny][nx]:
                if g[ny][nx] == '+' or g[ny][nx] == '-' or g[ny][nx] == '*':
                    cal.append(g[ny][nx])
                else:
                    num.append(g[ny][nx])
                v[ny][nx] = True
                dfs(nx, ny)
                if g[ny][nx] == '+' or g[ny][nx] == '-' or g[ny][nx] == '*':
                    cal.pop()
                else:
                    num.pop()
                v[ny][nx] = False

dx = [1, 0]
dy = [0, 1]
n = int(input())
g = [list(input().rstrip().split()) for _ in range(n)]
v = [[False] * n for _ in range(n)]

ans = []
num = deque([g[0][0]])
cal = deque()
v[0][0] = True
dfs(0, 0)
print(max(ans), min(ans))