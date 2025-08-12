import sys
import heapq
input = sys.stdin.readline
t = int(input())
for _ in range(t):
    n, k = map(int, input().split())
    time = [0] + list(map(int, input().split()))

    graph = [[] for _ in range(n+1)]
    indegree = [0] * (n+1)

    for _ in range(k):
        x, y = map(int,input().split())
        graph[x].append(y)
        indegree[y] += 1
        
    pq = []
    acc_time = [0] * (n+1)

    for i in range(1, n+1):
        if indegree[i] == 0:
            heapq.heappush(pq, (time[i], i))
        acc_time[i] = time[i]

    while pq:
        cur_acc_time, curr = heapq.heappop(pq)
        for next in graph[curr]:
            indegree[next] -= 1
            if indegree[next] == 0:
                acc_time[next] = cur_acc_time + time[next]
                heapq.heappush(pq, (acc_time[next], next))
    
    w = int(input())
    print(acc_time[w])