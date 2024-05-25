# 이걸 어떻게 'dfs + 백트래킹' 으로 하지..?!
# 음... 예제 3번이 총 개수가 60개가 나와야되는데 난 중복 포함돼서
# 120번 나오네..
import sys
from collections import deque
input = sys.stdin.readline

def dfs():
    if len(stack) == len(cal):
        all_sum = 0
        for i in range(len(num)):
            if i == 0:
                all_sum = num[i]
            else:
                if stack[i-1] == '+':
                    all_sum += num[i]
                elif stack[i-1] == '-':
                    all_sum -= num[i]
                elif stack[i-1] == '*':
                    all_sum *= num[i]
                else:
                    if all_sum < 0:
                        all_sum = all_sum * -1
                        all_sum //= num[i]
                        all_sum = all_sum * -1
                    else:
                        all_sum //= num[i]
        
        ans.append(all_sum)

        return
    
    for i in range(len(cal)):
        if not visited[i]:
            visited[i] = True
            stack.append(cal[i])
            dfs()
            visited[i] = False
            stack.pop()

n = int(input())
num = list(map(int, input().split()))
tmp = list(map(int, input().split()))

cal = []
for i in range(4):
    if i == 0:
        for j in range(tmp[i]):
            cal.append('+')
    elif i == 1:
        for j in range(tmp[i]):
            cal.append('-')
    elif i == 2:
        for j in range(tmp[i]):
            cal.append('*')
    elif i == 3:
        for j in range(tmp[i]):
            cal.append('/')

visited = [False] * len(cal)

cnt = 0

stack = []
ans = []
dfs()
print(max(ans))
print(min(ans))