import sys
input = sys.stdin.readline

n, m = map(int, input().split())
A = sorted([int(input()) for i in range(n)])

l, r = 0, 1

ans = 2000000000
while l <= r and r < n:
    diff = A[r] - A[l]
    
    if diff == m:
        ans = diff
        break

    if diff < m:
        r += 1
    elif diff > m:
        ans = min(diff, ans)
        l += 1

print(ans)