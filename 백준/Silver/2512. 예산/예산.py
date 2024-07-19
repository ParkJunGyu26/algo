import sys
input = sys.stdin.readline

def money_sum(target):
    cnt = 0
    for i in money:
        if i > target:
            cnt += target
        else:
            cnt += i
    
    return cnt

n = int(input())
money = list(map(int, input().split()))
m = int(input())

# 열린 구간
l, r = 1, max(money)+1

while l < r:
    mid = (l + r) // 2
    
    if money_sum(mid) <= m:
        l = mid + 1
    else:
        r = mid
print(l-1)