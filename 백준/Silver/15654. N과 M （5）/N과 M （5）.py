def dfs(idx):
    global n, m
    if idx == m:
        print(*s)
        return
    
    for i in l:
        if i not in s:
            s.append(i)
            dfs(idx+1)
            s.pop()

import sys
input = sys.stdin.readline

n, m = map(int, input().split())
l = sorted(list(map(int, input().split())))
s = []
dfs(0)