import sys
from collections import deque
input = sys.stdin.readline

def bfs(node):
    q = deque()
    q.append(node)

    while q:
        node = q.popleft()

        for i in graph[node]:
            if not visited[i]:
                q.append(i)
                visited[i] = True
    
    return 1


n, m = map(int, input().split())
graph = [[] for i in range(n+1)]
visited = [False] * (n+1)
for i in range(m):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)


# 1 -> 2 -> 5 -> 1
# 3 -> 4 -> 6
# [
#     0: [],
#     # 1: [2],
#     # 2: [5],
#     3: [4],
#     4: [6],
#     # 5: [1],
#     6: []
# ]

ans = []
for i in range(1, n+1):
    if not visited[i]:
        visited[i] = True
        ans.append(bfs(i))

print(len(ans))