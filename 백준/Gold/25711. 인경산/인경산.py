# 누적합..? 같은데

def make_diff(start_x, start_y, end_x, end_y):
    diff = ((abs(start_x - end_x) * abs(start_x - end_x)) + (abs(start_y - end_y) * abs(start_y - end_y)))**0.5

    return diff

import sys
input = sys.stdin.readline

n, q = map(int, input().split())
house = [0] * n

x = list(map(int, input().split()))
y = list(map(int ,input().split()))
for i in range(n):
    house[i] = (x[i], y[i])

upper_prefix = [0] * (n+1) # 시작 -> 끝
for i in range(n-1):
    start_x, start_y = house[i]
    end_x, end_y = house[i+1]

    diff = make_diff(start_x, start_y, end_x, end_y)

    if end_y > start_y:
        diff *= 3
    elif end_y == start_y:
        diff *= 2
    
    upper_prefix[i+1] = upper_prefix[i] + diff

lower_prefix = [0] * (n+1) # 끝 -> 시작
for i in range(n-1):
    start_x, start_y = house[i+1]
    end_x, end_y = house[i]

    diff = make_diff(start_x, start_y, end_x, end_y)

    if end_y > start_y:
        diff *= 3
    elif end_y == start_y:
        diff *= 2
    
    lower_prefix[i+1] = lower_prefix[i] + diff

for _ in range(q):
    i, j = map(int, input().split())
    if i <= j:
        print(upper_prefix[j-1] - upper_prefix[i-1])
    else:
        print(lower_prefix[i-1] - lower_prefix[j-1])