import sys
from collections import deque
input = sys.stdin.readline

n, k = map(int, input().split())
dq = deque([i for i in range(1, n+1)])
ans = []

idx = 1
while dq:
    if idx == k:
        ans.append(dq.popleft())
        idx = 1
    else:
        idx += 1
        dq.append(dq.popleft())

answer = '<'

for i in range(n):
    if i != n-1:
        answer += str(ans[i])
        answer += ', '
    else:
        answer += str(ans[i])
        answer += '>'

print(answer)