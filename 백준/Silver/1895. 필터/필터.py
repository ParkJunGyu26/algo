import sys
from collections import deque

input = sys.stdin.readline

pixel = []

row, column = map(int, input().split())
for _ in range(row):
    pixel.append(list(map(int, input().split())))

ans = []

for r in range(1, row-1):
    temp = []
    for c in range(1, column-1):
        tmp = pixel[r-1][c-1:c+2] + pixel[r][c-1:c+2] + pixel[r+1][c-1:c+2]
        tmp.sort()
        temp.append(tmp[4])
    ans.append(temp)

t = int(input())

cnt = 0

for i in ans:
    for j in i:
        if j >= t:
            cnt += 1

print(cnt)