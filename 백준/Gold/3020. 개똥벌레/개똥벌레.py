import sys
input = sys.stdin.readline

def binary_search(array, target):
    start, end = 0, len(array) - 1

    while start <= end:
        mid = (start + end) // 2

        if array[mid] < target:
            start = mid + 1
        else:
            end = mid - 1
    
    return start

n, h = map(int, input().split())

down = []
up = []
for i in range(n):
    if i % 2 == 0:
        down.append(int(input()))
    else:
        up.append(int(input()))

down.sort()
up.sort()

min_count = n
range_count = 0

for i in range(1, h+1):
    down_cnt = len(down) - binary_search(down, i - 0.5)
    up_cnt = len(up) - binary_search(up, h - i + 0.5)
    total_cnt = up_cnt + down_cnt

    if min_count == total_cnt:
        range_count += 1
    elif min_count > total_cnt:
        min_count = total_cnt
        range_count = 1

print(min_count, range_count)