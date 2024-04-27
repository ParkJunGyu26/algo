import sys
input = sys.stdin.readline

n, m = map(int, input().split())
baguni = [i for i in range(n+1)]
for _ in range(m):
    i, j = map(int, input().split())
    baguni[i], baguni[j] = baguni[j], baguni[i]

for _ in range(1, n+1):
    print(baguni[_], end=' ')