import sys
input = sys.stdin.readline

n, k = map(int, input().split())
A = list(map(int, input().split()))
prefix = []

prefix.append(sum(A[0:k]))
for i in range(1, n-k+1):
    prefix.append(prefix[i-1] - A[i-1] + A[k+i-1])

print(max(prefix))