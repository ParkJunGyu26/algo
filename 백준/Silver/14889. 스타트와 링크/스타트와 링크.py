# 팀을 n/2로 나눈다.
# 스타트 팀원 빼고 나머진 링크 팀

import sys
input = sys.stdin.readline

def dfs(num):
    global ans

    if len(start) == n//2:
        # 링크 팀 만들어주기
        for i in range(n):
            if i not in start:
                link.append(i)
        
        # print("start : ", start)
        # print("link : ", link)

        start_sum = 0
        link_sum = 0
        
        # 각 팀원 점수 구하기
        for i in range((n//2)-1):
            for j in range(i+1, (n//2)):
                start_sum += g[start[i]][start[j]] + g[start[j]][start[i]]
                link_sum += g[link[i]][link[j]] + g[link[j]][link[i]]

        
        ans = min(ans, abs(start_sum - link_sum))

        for i in range(n//2):
            link.pop()

        return
    
    for i in range(num, n):
        start.append(i)
        dfs(i+1)
        start.pop()


ans = 1e9
n = int(input())
g = [list(map(int, input().split())) for _ in range(n)]
start = []
link = []

dfs(0)

print(ans)