import sys

input = sys.stdin.readline

n = int(input())
num = sorted([int(input()) for i in range(n)])
for i in num:
    print(i)