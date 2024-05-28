import sys
from collections import deque
input = sys.stdin.readline

def bfs(node, graph, visited):
    q = deque([node])
    
    while q:
        node = q.popleft()
        
        for i in graph[node]:
            if not visited[i]:
                visited[i] = True
                q.append(i)
                
    return 1

def solution(n, computers):
    answer = 0
    graph = [[] for _ in range(n+1)]
    visited = [False] * (n+1)
    
    for i in range(n):
        for j in range(n):
            if computers[i][j]:
                graph[i+1].append(j+1)
                
    for i in range(1, n+1):
        if not visited[i]:
            visited[i] = True
            answer += bfs(i, graph, visited)
    
    print(graph)
    return answer