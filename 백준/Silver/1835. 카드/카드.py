import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
stack = []
li = [i for i in range(1, n+1)]
dq = deque(li)
cnt = 0
idx = 1

while dq:
    if idx == cnt:
        stack.append(dq.popleft())
        idx += 1
        cnt = 0
    else:
        cnt += 1
        dq.append(dq.popleft())

ans = [0] * n
for i in range(len(stack)):
    ans[stack[i]-1] = (i+1)

print(*ans)