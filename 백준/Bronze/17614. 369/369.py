import sys

input = sys.stdin.readline

n = int(input())
cnt = n // 3
ans = 0
if n < 10:
    print(cnt)
else:
    for i in range(10, n+1):
        for j in str(i):
            if j == '3' or j == '6' or j == '9':
                ans += 1
    print(ans + 3)