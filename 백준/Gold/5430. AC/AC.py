from collections import deque
import sys
input = sys.stdin.readline

t = int(input())
for i in range(t):
    check = True # R의 개수에 따라 앞에서 빼거나 뒤에서 빼거나
    ans = deque()
    func = []

    p = input().rstrip() # 명령어
    n = int(input()) # 배열 정수 개수
    x = input().rstrip() # 배열

    # print("p : ", p)
    # 배열 개수가 0개일 경우
    exc = True
    if not n:
        for _ in p:
            if _ == 'D':
                exc = False
                break
        if exc:
            print(x)
        else:
            print("error")
        continue

    number = ''
    for s in x:
        if s == ',' or s == ']':
            ans.append(number)
            number = ''
            continue
        if s != '[' and s != ']':
            number += s
    
    exce = True
    for s in p:
        if s == 'R':
            if check:
                check = False
            else:
                check = True
        else:
            # print("ans1  : ", ans)
            if not ans:
                exce = False
                break

            if check:
                ans.popleft()
            else:
                ans.pop()
            # print("ans2  : ", ans)

    if not exce:
        print("error")
        continue
    # print("ans : ", ans)
    answer = []
    if not check:
        ans = list(ans)[::-1]

    answer.append('[')
    for s in range(len(ans)):
        if s != len(ans)-1:
            answer.append(ans[s])
            answer.append(',')
        else:
            answer.append(ans[s])
    answer.append(']')
    print(''.join(answer))