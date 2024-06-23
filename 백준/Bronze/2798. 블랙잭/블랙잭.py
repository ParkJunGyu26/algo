from itertools import combinations as cb

n, m = map(int, input().split())
arr = list(map(int, input().split()))

# 절대값으로 비교할 부분
tmp = m

for x, y, z in cb(arr, 3):
    sum = x+y+z
    if sum <= m and abs(m - sum) < tmp:
        tmp = abs(m - sum)
        ans = sum

print(ans)