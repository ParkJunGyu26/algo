import sys
import copy
input = sys.stdin.readline

def up_down(graph):
    ans = []
    for i in range(n-1, -1, -1):
        ans.append(list(graph[i]))
    
    return ans

def right_left(graph):
    ans = []
    for i in range(n):
        ans.append(list(graph[i][::-1]))
    
    return ans

def right(graph):
    ans = []
    for j in range(m):
        new = []
        for i in range(n-1, -1, -1):
            new.append(graph[i][j])
        ans.append(list(new))
    
    return ans

def left(graph):
    ans = []
    for j in range(m-1, -1, -1):
        new = []
        for i in range(n):
            new.append(graph[i][j])
        ans.append(list(new))
    
    return ans

def split_right(graph):
    ans = []
    for garo_half in range(2): # 가로를 절반으로 나눔
        for i in range(n//2, n): # 세로를 절반으로 나눔
            target = []
            for j in range(garo_half * m//2, (garo_half+1) * m // 2): # 절반인 가로를
                target.append(graph[i][j])
            for j in range(garo_half * m//2, (garo_half+1) * m // 2): # 절반인 가로를
                target.append(graph[i-n//2][j])
            ans.append(list(target))
    
    return ans

def split_left(graph):
    ans = []
    for garo_half in range(1, -1, -1):
        for i in range(n//2): # 세로를 절반으로 나눔
            target = []
            for j in range(garo_half * m//2, (garo_half+1) * m // 2): # 절반인 가로를
                target.append(graph[i][j])
            for j in range(garo_half * m//2, (garo_half+1) * m // 2): # 절반인 가로를
                target.append(graph[i-n//2][j])
            ans.append(list(target))
    
    return ans

n, m, r = map(int, input().split())
graph = [list(map(int, input().split())) for i in range(n)]
T = list(map(int, input().split()))

for i in range(r):
    t = T[i]
    if t == 1:
        graph = up_down(graph)
    elif t == 2:
        graph = right_left(graph)
    elif t == 3:
        graph = right(graph)
    elif t == 4:
        graph = left(graph)
    elif t == 5:
        graph = split_right(graph)
    else:
        graph = split_left(graph)
    
    n = len(graph) # 세로
    m = len(graph[0]) # 가로

for i in range(n):
    for j in range(m):
        print(graph[i][j], end=" ")
    print()