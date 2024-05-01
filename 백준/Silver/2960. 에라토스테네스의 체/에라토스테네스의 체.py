import sys
input = sys.stdin.readline

n, k = map(int, input().split())
num = [1 for i in range(n+1)]
count = 0

for i in range(2, n+1):
    if num[i]:
        for j in range(1, n//i+1):
            if num[i*j]:
                count += 1
                num[i*j] = 0
            if count == k:
                ans = i*j
                break

# print(num)

print(ans)

# 2 3 4 5 6 7 8 9 10
# 2 4 6 8 10
# 3