import sys
input = sys.stdin.readline

def dfs():
    if len(ans) == m:
        print(*ans)
        return

    for i in range(len(num)):
        ans.append(num[i])
        dfs()
        ans.pop()

n, m = map(int, input().split())
num = sorted(list(set(list(map(int, input().split())))))
ans = []

dfs()