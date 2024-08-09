# 유니온 파인드
# 사이클 생기는지 안생기는지 체크

import sys
input = sys.stdin.readline

def find_parent(parent, node):
    if parent[node] != node: # 자기 자신이 부모가 아니라면
        parent[node] = find_parent(parent, parent[node])
    
    return parent[node]

def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)

    # 작은 노드가 부모가 되도록 통일
    if a < b: 
        parent[b] = a
    else:
        parent[a] = b

v, e = map(int, input().split())

# 부모 테이블에는 일단, 자기 자신이 부모로 초기화
parent = [0] * v
for i in range(v):
    parent[i] = i

ans = 0
# 간선을 하나씩 입력하고, 두 노드의 부모가 같다면 종료하기
edge = []
for i in range(e):
    a, b = map(int, input().split())
    edge.append((a, b))

ans = 0
for i in range(e):
    a, b = edge[i]
    if find_parent(parent, a) == find_parent(parent, b):
        ans = i+1
        break
    else:
        union_parent(parent, a, b)

print(ans)