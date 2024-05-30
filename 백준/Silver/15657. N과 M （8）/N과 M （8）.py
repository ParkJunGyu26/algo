import sys
input = sys.stdin.readline

def dfs(count):

    if len(ans) == m:
        print(*ans)
        return

    for i in range(count, n):
        ans.append(num[i])
        dfs(i)
        ans.pop()

n, m = map(int, input().split())
num = sorted(list(map(int, input().split())))
ans = []

dfs(0)