import sys
input = sys.stdin.readline

def dfs(start):
    global ans_min, ans_max

    if len(hubo) == k:
        # 대피소 중 가장 가까운 점
        ttmp = []
        for x,y in house:
            if (x, y) not in hubo:
                # 대피소와 해당 위치의 거리 저장
                tmp = []

                for dx, dy in hubo:
                    dist = abs(dx-x)+abs(dy-y)
                    tmp.append(dist)
                # 대피소 중 가까운 점 찾기
                ttmp.append(min(tmp))
        
        ans_min = min(ans_min, max(ttmp))

        return
    
    for i in range(start, n):
        hubo.append(house[i])
        dfs(i+1)
        hubo.pop()

n, k = map(int, input().split())
house = []
hubo = []

for i in range(n):
    x, y = map(int, input().split())
    house.append((x, y))

ans_min = 1e9
ans_max = 0

dfs(0)

print(ans_min)