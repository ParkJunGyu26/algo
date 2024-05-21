import sys
sys.setrecursionlimit(10**5)
input = sys.stdin.readline

def dfs(node):
    for i in g[node]:
        if not v[i]:
            v[i] = True
            dfs(i)
    
    return 1

t = int(input())
for _ in range(t):
    n = int(input())
    g = [[] for i in range(n+1)]
    v = [False] * (n+1)
    li = list(map(int, input().split()))
    for i in range(1, n+1):
        g[i].append(li[i-1])
        g[li[i-1]].append(i)
    
    dx = [1, -1, 0, 0]
    dy = [0, 0, -1, 1]
    ans = 0
    for i in range(1, n+1):
        if not v[i]:
            v[i] = True
            ans += dfs(i)
    print(ans)