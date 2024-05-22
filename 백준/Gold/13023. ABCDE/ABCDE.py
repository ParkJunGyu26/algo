import sys
sys.setrecursionlimit(10**5)
# 깊이가 4 이상인 게 있냐? -> 맞음
# 시작 시점은 상관없음
input = sys.stdin.readline

def dfs(node, depth):
    global check

    if depth == 4:
        check = True
        return 
    
    for i in g[node]:
        if not v[i]:
            v[i] = True
            dfs(i, depth+1)
            v[i] = False

n, m = map(int, input().split())
g = [[] for _ in range(n)]
v = [False] * n

for _ in range(m):
    a, b = map(int, input().split())
    g[a].append(b)
    g[b].append(a)

check = False
for i in range(n):
    if not v[i]:
        v[i] = True
        dfs(i, 0)
        v[i] = False

if check:
    print(1)
else:
    print(0)