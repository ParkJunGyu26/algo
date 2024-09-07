import sys
input = sys.stdin.readline

def one(x, y):
    ans = -int(1e9)

    if x + 3 < n:
        ans = sum(graph[y][x:x+4])

    return ans

def two(x, y):
    ans = -int(1e9)

    if y + 3 < n:
        ans = 0
        for i in range(y, y+4):
            ans += graph[i][x]
    
    return ans

def three(x, y):
    ans = -int(1e9)

    if x + 2 < n and y + 1 < n:
        ans = 0
        ans += graph[y][x] + graph[y][x+1] + graph[y+1][x+1] + graph[y+1][x+2]
    
    return ans

def four(x, y):
    ans = -1e9

    if -1 < x - 1 and y + 2 < n:
        ans = 0
        ans += graph[y][x] + graph[y+1][x] + graph[y+1][x-1] + graph[y+2][x-1]

    return ans

def five(x, y):
    ans = -1e9

    if x + 2 < n and y + 1 < n:
        ans = sum(graph[y][x:x+3])
        ans += graph[y+1][x+2]

    return ans

def six(x, y):
    ans = -1e9

    if -1 < x-1 and y+2 < n:
        ans = 0
        for i in range(y, y+3):
            ans += graph[i][x]
        ans += graph[y+2][x-1]

    return ans

def seven(x, y):
    ans = -1e9

    if x+2 < n and y+1 < n:
        ans = 0
        ans += graph[y][x] + sum(graph[y+1][x:x+3])
    
    return ans

def eight(x, y):
    ans = -1e9

    if -1 < x-1 and y+2 < n:
        ans = 0
        ans += graph[y][x] + graph[y][x-1] + graph[y+1][x-1] + graph[y+2][x-1]

    return ans

def nine(x, y):
    ans = -1e9

    if x+2 < n and y+1 <n:
        ans = 0
        ans += graph[y][x] + graph[y][x+1] + graph[y+1][x+1] + graph[y][x+2]

    return ans

def ten(x, y):
    ans = -1e9

    if -1 < x-1 and y+2 <n:
        ans = 0
        ans += graph[y][x] + graph[y+1][x] + graph[y+2][x] + graph[y+1][x-1]

    return ans

def eleven(x, y):
    ans = -1e9

    if x+2 < n and -1 < y-1:
        ans = 0
        ans += graph[y][x] + graph[y][x+1] + graph[y-1][x+1] + graph[y][x+2]

    return ans

def twelve(x, y):
    ans = -1e9

    if x+1 < n and y+2 < n:
        ans = 0
        ans += graph[y][x] + graph[y+1][x] + graph[y+1][x+1] + graph[y+2][x]

    return ans

def thirteen(x, y):
    ans = -1e9

    if x+1 < n and y+1 < n:
        ans = 0
        ans += sum(graph[y][x:x+2]) + sum(graph[y+1][x:x+2])
    
    return ans

def solution(n):
    
    ans = -int(1e9)
    for y in range(n):
        for x in range(n):
            ans = max(ans, one(x, y), two(x, y), three(x, y), four(x, y), five(x, y), six(x, y), seven(x, y), eight(x, y), nine(x, y), ten(x, y), eleven(x, y), twelve(x, y), thirteen(x, y))

    return ans

tmp = 1
while 1:
    n = int(input())
    if n == 0:
        break
    
    graph = [list(map(int, input().split())) for _ in range(n)]
    ans = solution(n)
    print(str(tmp) + ".", ans)

    tmp += 1