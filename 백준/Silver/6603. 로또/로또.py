# 6 보다 큰 수 중 6자리 출력하기
# kC6

import sys
input = sys.stdin.readline

def dfs():
    if len(ans) == 6:
        print(*ans)
        return
    
    for i in range(len(num)):
        if not visited[i]:
            if not ans:
                visited[i] = True
                ans.append(num[i])
                dfs()
                visited[i] = False
                ans.pop()
            else:
                if ans[-1] < num[i]:
                    visited[i] = True
                    ans.append(num[i])

                    dfs()

                    visited[i] = False
                    ans.pop()

while (True):
    tmp = list(map(int, input().split()))
    if tmp[0] == 0:
        break

    num = [tmp[i] for i in range(1, len(tmp))]
    visited = [False] * (tmp[0])
    ans = []
    dfs()
    print()