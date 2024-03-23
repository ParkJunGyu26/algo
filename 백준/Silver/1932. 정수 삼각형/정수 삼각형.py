import sys

input = sys.stdin.readline

n = int(input())

nums = []
for i in range(n):
    if i == 0:
        num = int(input())
        nums.append([num])
    else:
        nums.append(list(map(int, input().split())))

dp = []
for i in range(n):
    if i == 0:
        dp.append(nums[i])
    else:
        target = []
        for j in range(len(nums[i])):
            # 제일 왼쪽의 숫자
            if j == 0:
                target.append(dp[i-1][0] + nums[i][0])
            # 제일 오른쪽의 숫자
            elif j == len(nums[i]) - 1:
                target.append(dp[i-1][-1] + nums[i][-1])
            # 나머지 숫자
            else:
                target.append(max(dp[i-1][j], dp[i-1][j-1]) + nums[i][j])
        dp.append(target)

print(max(max(dp)))