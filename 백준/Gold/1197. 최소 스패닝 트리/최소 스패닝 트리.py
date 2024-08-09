# 최소신장트리(크루스칼)

import sys
input = sys.stdin.readline

def find_parent(parent, x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

V, E = map(int, input().split())

# 부모 테이블
parent = [0] * (V+1)
for i in range(1, V+1):
    parent[i] = i

edges = [] # 간선 정보
total_cost = 0 # 최소 신장 트리 계산

for _ in range(E):
    a, b, cost = map(int, input().split())
    edges.append((cost, a, b))

edges.sort()

for i in range(E):
    cost, a, b = edges[i]
    # find로 부모 노드 다르면 사이클 발생하지 않음
    # 사이클 발생하지 않으면 union으로 MST 추가
    if find_parent(parent, a) != find_parent(parent, b):
        union_parent(parent, a, b)
        total_cost += cost

print(total_cost)