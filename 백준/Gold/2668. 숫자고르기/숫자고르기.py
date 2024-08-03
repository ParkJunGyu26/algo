import sys
input = sys.stdin.readline

def dfs(start, node):
    visited[node] = True

    for i in graph[node]:
        if not visited[i]:
            dfs(start, i)
        elif start == i:
            ans.append(start)
            return

n = int(input())
graph = [[] for _ in range(n+1)]

for i in range(1, n+1):
    graph[i].append(int(input()))

ans = []
for i in range(1, n+1):
    visited = [False] * (n+1)
    dfs(i, i)

ans.sort()
print(len(ans), *ans, sep="\n")