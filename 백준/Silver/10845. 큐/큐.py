import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
q = deque()

for _ in range(n):
    tmp = input().rstrip().split(' ')
    if tmp[0] == 'push':
        q.append(tmp[1])
    elif tmp[0] == 'pop':
        if q:
            print(q.popleft())
        else:
            print(-1)
    elif tmp[0] == 'front':
        if q:
            print(q[0])
        else:
            print(-1)
    elif tmp[0] == 'back':
        if q:
            print(q[-1])
        else:
            print(-1)
    elif tmp[0] == 'size':
        print(len(q))
    elif tmp[0] == 'empty':
        if q:
            print(0)
        else:
            print(1)