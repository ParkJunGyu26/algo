import sys
sys.setrecursionlimit(10**5)
input = sys.stdin.readline

# 부모 노드 통일시켜주기
def union(a, b):
    a = find(a)
    b = find(b)
    if a == b: return
    if a < b:
        graph[b] = a
    else:
        graph[a] = b

# 부모 노드 찾기
def find(x):
    if x == graph[x]:
        return x
    graph[x] = find(graph[x])
    return graph[x]

n, m = map(int, input().split())
graph = [i for i in range(n+1)]

for _ in range(m):
    num, a, b = map(int, input().split())
    if num == 0:
        union(a, b)
    else:
        if find(a) == find(b):
            print("YES")
        else:
            print("NO")