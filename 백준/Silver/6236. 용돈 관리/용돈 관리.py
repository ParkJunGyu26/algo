import sys
input = sys.stdin.readline

n, m = map(int, input().split())
money = [int(input()) for i in range(n)]

l, r = max(money), sum(money)

ans = 0
while l <= r:
    mid = (l + r) // 2

    total = 0
    count = 0
    for i in money:
        if total + i <= mid:
            total += i
        else:
            count += 1
            total = 0
            total += i
    
    if total:
        count += 1
    
    if count <= m:
        ans = mid
        r = mid - 1
    else:
        l = mid + 1

print(ans)