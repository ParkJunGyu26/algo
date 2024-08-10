import sys
input = sys.stdin.readline

def dfs(node):
    global ans

    # 삭제된 노드의 경우 패스
    if node == target:
        return
    
    if len(graph[node]) == 1:
        if target in graph[node]:
            ans += 1

    # 리프 노드인 경우
    if len(graph[node]) == 0:
        ans += 1
    
    for i in graph[node]:
        if not visited[i]:
            visited[i] = 1
            dfs(i)
            visited[i] = 0

n = int(input())
parent = list(map(int, input().split()))
graph = [[] for _ in range(n)]
visited = [0] * n

for i in range(n):
    if parent[i] == -1:
        root = i
        continue

    graph[parent[i]].append(i)

target = int(input())

ans = 0

visited[root] = 1
dfs(root)
print(ans)