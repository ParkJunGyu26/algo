import sys
input = sys.stdin.readline

def dfs(count):
    if len(ans) == m:
        print(*ans)
        return

    for i in range(count, len(num)):
        ans.append(num[i])
        dfs(i)
        ans.pop()

n, m = map(int, input().split())
num = sorted(list(set(list(map(int, input().split())))))
ans = []

dfs(0)