import sys
input = sys.stdin.readline

def dfs():

    if len(ans) == m:
        print(*ans)
        return

    for i in range(n):
        ans.append(num[i])
        dfs()
        ans.pop()

n, m = map(int, input().split())
num = sorted(list(map(int, input().split())))
ans = []

dfs()