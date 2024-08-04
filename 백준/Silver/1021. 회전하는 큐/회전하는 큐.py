import sys
from collections import deque

def index_position(number):
    for i in range(len(num)):
        if number == num[i]:
            return i
        
def left(target_num):
    cnt = 0
    while 1:
        number = num.popleft()
        if number == target_num:
            target.popleft()
            return cnt
        
        cnt += 1
        num.append(number)

def right(target_num):
    cnt = 1
    while 1:
        number = num.pop()
        if number == target_num:
            target.popleft()
            return cnt
        
        cnt += 1
        num.appendleft(number)

n, m = map(int, input().split())
target = deque(list(map(int, input().split())))
num = deque([i for i in range(1, n+1)])

ans = 0
while target:
    if index_position(target[0]) <= len(num) // 2:
        ans += left(target[0])
    else:
        ans += right(target[0])

print(ans)