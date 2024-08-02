import sys
input = sys.stdin.readline

# 하.. 이진탐색으로 풀려니깐 어렵네... 다음에 또 풀어보자
n, h = map(int, input().split())
bottom = [0] * (h+1)
top = [0] * (h+1)

min_count = n
range_count = 0

for i in range(n):
    tmp = int(input())
    if i % 2 == 0:
        bottom[tmp] += 1
    else:
        top[tmp] += 1

for i in range(h-1, 0, -1):
    bottom[i] += bottom[i+1]
    top[i] += top[i+1]

for i in range(1, h+1):
    if min_count > (bottom[i] + top[h-i+1]):
        min_count = bottom[i] + top[h-i+1]
        range_count = 1
    elif min_count == (bottom[i] + top[h-i+1]):
        range_count += 1

print(min_count, range_count)
# 누적합도 쉽지 않네...