import sys
input = sys.stdin.readline

s, c = map(int, input().split())
arr = [int(input()) for _ in range(s)]

l, r = 1, max(arr)

res = 0
while l <= r:
    mid = (l+r)//2

    cnt = 0
    for i in arr:
        cnt += (i//mid)
    
    if cnt < c:
        r = mid-1
    elif cnt >= c:
        res = max(res, mid)
        l = mid+1

ans = sum(arr) - (c*res)
print(ans)