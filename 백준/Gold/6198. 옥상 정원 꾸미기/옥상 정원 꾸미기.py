import sys

input = sys.stdin.readline

n = int(input())
li = [int(input()) for i in range(n)]
stack = []
ans = 0

for i in range(n):
    while stack and stack[-1] <= li[i]:
        stack.pop()
    
    stack.append(li[i])
    ans += len(stack) - 1

print(ans)