import sys
input = sys.stdin.readline

k = int(input())
stack = []

for i in range(k):
    num = int(input())
    if not num:
        stack.pop()
    else:
        stack.append(num)

print(sum(stack))