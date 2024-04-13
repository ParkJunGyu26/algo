import sys
from collections import deque

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n, m = map(int, input().split())
    li = deque(list(map(int, input().split())))
    if len(li) == 1:
        print(1)
        continue
    
    # 인덱스 번호
    q = deque([i for i in range(n)])
    cnt = 1
    
    while True:
        li_max = max(li)
        target = li.popleft()
        if li_max == target:
            if q.popleft() == m:
                break
            else:
                cnt += 1
        else:
            li.append(target)
            q.append(q.popleft())
    
    print(cnt)