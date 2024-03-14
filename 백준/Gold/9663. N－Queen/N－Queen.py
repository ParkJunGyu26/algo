import sys

input = sys.stdin.readline
n= int(input())
ans = 0
row = [0] * n

def attack(x):
    for i in range(x):
        if row[x] == row[i] or abs(row[x] - row[i]) == abs(x-i):
            return True
    return False
    

def dfs(start):
    global ans

    if start == n:
        ans += 1
    else:
        for i in range(n):
            row[start] = i
            if not attack(start):
                dfs(start+1)

dfs(0)

print(ans)