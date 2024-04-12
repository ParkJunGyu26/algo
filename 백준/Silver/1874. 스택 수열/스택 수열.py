import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
ans = []
stack = [0]
dq = deque([i for i in range(1, n+1)])
check = False

for i in range(n):
    num = int(input())
    while True:
        if stack[-1] == num:
            stack.pop()
            ans.append('-')
            break
        else:
            if len(dq) == 0:
                check = True
                break

            stack.append(dq.popleft())
            ans.append('+')
    

if check:
    print("NO")
else:
    for a in ans:
        print(a)