import sys
from collections import deque
input = sys.stdin.readline

left = deque(list(input().rstrip()))
right = deque([])

t = int(input())
for _ in range(t):
    tmp = list(input().rstrip().split())
    if tmp[0] == 'P':
        left.append(tmp[1])
    
    if tmp[0] == 'L':
        if len(left):
            right.appendleft(left.pop())
    
    if tmp[0] == 'D':
        if len(right):
            left.append(right.popleft())

    if tmp[0] == 'B':
        if len(left):
            left.pop()

print(''.join(left+right))