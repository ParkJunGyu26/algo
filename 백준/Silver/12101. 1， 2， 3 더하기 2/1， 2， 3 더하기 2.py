def dfs():
    if sum(num) >= n:
        if sum(num) == n:
            ans.append(list(num))
        return
    
    for i in range(1, 4):
        num.append(i)
        dfs()
        num.pop()

n, k = map(int, input().split())
num = []

ans = [[]]
dfs()

if k < len(ans):
    print(*ans[k], sep='+')
else:
    print(-1)