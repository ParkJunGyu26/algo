import sys

input = sys.stdin.readline

ans = [0 for i in range(10)]
target = 1

for i in range(3):
    target *= int(input())

for i in str(target):
    ans[int(i)] += 1

for i in ans:
    print(i)