import sys
from collections import deque
input = sys.stdin.readline

def bfs(start):
    q = deque([(start)])

    while q:
        node = q.popleft()

        if graph[node] != 0:
            for i in range(1, graph[node]+1):
                update_node = node + i
                if update_node <= n and not visited[update_node]:
                    q.append(update_node)
                    visited[update_node] = visited[node] + 1

n = int(input())
graph = [0] + list(map(int, input().split()))
if n == 1:
    if graph[1] == 0:
        print(0)
    else:
        print(1)
else:
    visited = [0] * (n+1)
    bfs(1)

    if visited[n]:
        print(visited[n])
    else:
        print(-1)