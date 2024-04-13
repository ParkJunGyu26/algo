import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
li = [i for i in range(n, 0, -1)]
dq = deque(li)

while len(dq) > 1:
    dq.pop()
    dq.appendleft(dq.pop())

print(*dq)