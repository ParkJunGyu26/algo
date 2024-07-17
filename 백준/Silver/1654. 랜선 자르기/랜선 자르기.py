import sys
input = sys.stdin.readline

def check(length):
    cnt = 0
    for i in lan:
        cnt += i // length
    
    return cnt >= n

k, n = map(int, input().split())
lan = sorted([int(input()) for i in range(k)])

# 열린 구간
l, r = 1, max(lan)+1

while l < r:
    mid = (l+r)//2

    if check(mid):
        l = mid+1
    else:
        r = mid
print(l-1)