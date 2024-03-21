import sys
input = sys.stdin.readline

t = int(input())
for i in range(t):
    tmp = []
    n = int(input())
    for k in range(2):
        tmp.append(list(map(int, input().split())))
    for j in range(1, n):
        if j == 1:
            tmp[0][j] += tmp[1][j-1]
            tmp[1][j] += tmp[0][j-1]
        else:
            tmp[0][j] += max(tmp[1][j-1], tmp[1][j-2])
            tmp[1][j] += max(tmp[0][j-1], tmp[0][j-2])
    print(max(tmp[0][n-1], tmp[1][n-1]))