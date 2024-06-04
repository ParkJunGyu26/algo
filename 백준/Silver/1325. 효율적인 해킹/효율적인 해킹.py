import sys
from collections import deque
input = sys.stdin.readline

def bfs(node):
    cnt = 1
    q = deque([(node)])

    while q:
        target = q.pop()

        for i in g[target]:
            if not visited[i]:
                visited[i] = True
                q.append(i)
                cnt += 1
    
    return cnt


n, m = map(int, input().split())
g = [[] for _ in range(n+1)]
for i in range(m):
    a, b = map(int, input().split())
    g[b].append(a)

res = [[] for _ in range(n+1)]

hack_max = 0

for i in range(1, n+1):
    if g[i]:
        visited = [False] * (n+1)
        visited[i] = True
        cnt = bfs(i)
        hack_max = max(hack_max, cnt)
        res[i].append(cnt)

# print("res : ", res)

tmp = 0

for i in res:
    if i:
        for j in i:
            if hack_max == j:
                print(tmp, end=' ')
    tmp += 1