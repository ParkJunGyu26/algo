import sys
import heapq
input = sys.stdin.readline

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def dijkstra():
    hq = []
    heapq.heappush(hq, (graph[0][0], 0, 0)) # (비용, x, y)
    distance[0][0] = 0
    
    while hq:
        cost, x, y = heapq.heappop(hq)

        if x == n-1 and y == n-1:
            print(f'Problem {count}: {distance[y][x]}')
            break

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if -1 < nx < n and -1 < ny < n:
                new_cost = cost + graph[ny][nx]

                if new_cost < distance[ny][nx]:
                    distance[ny][nx] = new_cost
                    heapq.heappush(hq, (new_cost, nx, ny))

count = 1

while 1:
    n = int(input())
    if n == 0:
        break
    graph = [list(map(int, input().split())) for _ in range(n)]
    distance = [[int(1e9)] * n for _ in range(n)]

    dijkstra()
    count += 1