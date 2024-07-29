import sys
input = sys.stdin.readline

m, n = map(int, input().split())
L = sorted(list(map(int, input().split())))

l, r = 1, L[n-1]+1
ans = 0

# 우측 탐색(최대값 찾기)
while l < r:
    mid = (l + r) // 2

    count = 0
    for i in L:
        if i < mid:
            continue
        else:
            count += i // mid
    
    if count > m:
        l = mid + 1
        ans = mid
    elif count == m:
        l = mid + 1
        ans = mid
    elif count < m:
        r = mid
        
print(ans)