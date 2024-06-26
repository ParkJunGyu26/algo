import sys
input = sys.stdin.readline

stick = []
n = int(input())
for i in range(n):
    stick.append(int(input()))

height = 0
ans = 0
for i in range(n-1, -1, -1):
    if stick[i] > height:
        ans += 1
        height = stick[i]

print(ans)