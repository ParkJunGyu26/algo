# 유니온 파인드
# 0 a b -> Union
# 1 a b -> Find
import sys

sys.setrecursionlimit(10**5)
input = sys.stdin.readline

n, m = map(int, input().split())
p = [i for i in range(n+1)]

def find(a):
    if a != p[a]:
        p[a] = find(p[a])
    return p[a]

def union(a, b):
    pa = find(a)
    pb = find(b)
    p[pa] = pb

for i in range(m):
    num, a, b = map(int, input().split())
    if num == 0:
        union(a, b)
    else:
        if find(a) == find(b):
            print("YES")
        else:
            print("NO")