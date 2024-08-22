import sys
from collections import deque
input = sys.stdin.readline

n, k, m = map(int, input().split())
q = deque([i for i in range(1, n+1)])

stack = []
cnt = 0
change = False # 원 돌리는 방향
change_cnt = 0

while q:
    if not change:
        target = q.popleft()
        cnt += 1

        if cnt == k:
            stack.append(target)
            cnt = 0
            change_cnt += 1
            if change_cnt == m:
                change = not change
                change_cnt = 0
        else:
            q.append(target)
    else:
        target = q.pop()
        cnt += 1

        if cnt == k:
            stack.append(target)
            cnt = 0
            change_cnt += 1
            if change_cnt == m:
                change = not change
                change_cnt = 0
        else:
            q.appendleft(target)

print(*stack, sep="\n")