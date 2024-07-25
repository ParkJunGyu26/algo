import sys
input = sys.stdin.readline

n, c = map(int, input().split())
house = sorted([int(input()) for _ in range(n)])

l, r = 1, house[-1] - house[0]
ans = 0

while l <= r:
    mid = (l + r) // 2
    
    now = house[0]
    cnt = 1
    for i in range(1, n):
        if house[i] >= now + mid:
            cnt += 1
            now = house[i]
    
    if cnt >= c:
        l = mid + 1
        ans = mid
    else:
        r = mid - 1

print(ans)