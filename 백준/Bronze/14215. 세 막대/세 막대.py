import sys

input = sys.stdin.readline

num = sorted(list(map(int, input().split())))

a, b, c = num

if a + b <= c:
    c = a+b-1

print(a+b+c)