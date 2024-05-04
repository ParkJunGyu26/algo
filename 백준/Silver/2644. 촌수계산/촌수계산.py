from collections import deque

def bfs(start):
    q = deque()
    visited = [0 for _ in range(n+1)]

    q.append(start)
    visited[start] = 1
    
    while q:
        node = q.popleft()

        for i in graph[node]:
            if visited[i] == 0:
                visited[i] = 1
                result[i] = result[node] + 1
                q.append(i)

n = int(input())
a, b = map(int, input().split())
m = int(input())

graph = [[] for _ in range(n+1)]
result = [0 for _ in range(n+1)]

for _ in range(m):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)

bfs(a)
if result[b] != 0:
    print(result[b])
else:
    print(-1)