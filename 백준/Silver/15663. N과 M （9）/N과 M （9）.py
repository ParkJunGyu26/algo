import sys
input = sys.stdin.readline

def dfs():
    duplicate = 0
    if len(ans) == m:
        print(*ans)

        return
    
    for i in range(n):
        if not visited[i] and A[i] != duplicate:
            duplicate = A[i]
            visited[i] = 1
            ans.append(A[i])
            dfs()
            ans.pop()
            visited[i] = 0

n, m = map(int, input().split())
A = sorted(list(map(int, input().split())))
visited = [0] * n

ans = []
dfs()