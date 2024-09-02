# 완전탐색( N^2 은 시간 초과 )
import sys
input = sys.stdin.readline

n, k = map(int, input().split())
tmp = input().rstrip()
number = [int(tmp[i]) for i in range(n)]

answer = [number[0]]
index = 1
while index < n:
    if k == 0:
        for i in range(index, len(number)):
            answer.append(number[i])
        break

    # 스택의 top 보다 큰 경우
    while answer and answer[-1] < number[index] and k > 0:
        answer.pop()
        k -= 1
    
    # 스택의 top 보다 작은 경우
    answer.append(number[index])
    
    index += 1

index = 0
MIN = sorted(answer, reverse=True)
ans = []
while index < len(answer) and k < len(answer) - len(ans): # 지울 게 남았고, 동일한 숫자가 있는 경우
    if k == 0:
        for i in range(index, len(answer)):
            ans.append(answer[i])
        break
    if answer[index] > MIN[-1]:
        ans.append(answer[index])
    else:
        if answer[index] <= answer[index+1]:
            k -= 1
        else:
            ans.append(answer[index])
    index += 1

print(*ans, sep="")