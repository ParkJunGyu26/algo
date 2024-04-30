import sys
sys.setrecursionlimit(10**5)
input = sys.stdin.readline

def union(a, b):
    a = find(a)
    b = find(b)
    if a == b: return

    if a > b:
        p[a] = b
    else:
        p[b] = a

def find(x):
    if x == p[x]:
        return x
    
    p[x] = find(p[x])
    return p[x]


n = int(input())
m = int(input())
p = [i for i in range(n+1)]
city = []

for i in range(n):
    city.append(list(map(int, input().split())))

for i in range(n):
    for j in range(n):
        if city[i][j]:
            union(i+1, j+1)

# print("p : ", p)

ans = list(map(int, input().split()))

check = True
for t in range(m-1):
    target1 = find(ans[t])
    target2 = find(ans[t+1])
    if target1 != target2:
        check = False
        break

if check:
    print("YES")
else:
    print("NO")