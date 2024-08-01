import sys
input = sys.stdin.readline

def solution(hubo):
    cnt = 0
    for i in range(1, len(hubo)):
        cnt += abs(num[hubo[i]] - num[hubo[i-1]])

    return cnt

def dfs():
    global ans
    if len(hubo_index) == n:
        ans = max(ans, solution(hubo_index))
        return
    
    for i in range(n):
        if not visited[i]:
            visited[i] = 1
            hubo_index.append(i)
            dfs()
            visited[i] = 0
            hubo_index.pop()
    

n = int(input())
num = list(map(int, input().split()))
visited = [0] * n

ans = 0
hubo_index = []
dfs()
print(ans)