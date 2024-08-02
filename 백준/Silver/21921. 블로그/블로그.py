import sys
input = sys.stdin.readline

N, X = map(int, input().split())
today = list(map(int, input().split()))

current_sum = sum(today[:X])
max_sum = current_sum
max_count = 1

for i in range(1, N-X+1):
    current_sum = current_sum - today[i-1] + today[i+X-1]
    if current_sum > max_sum:
        max_sum = current_sum
        max_count = 1
    elif current_sum == max_sum:
        max_count += 1

if max_sum == 0:
    print("SAD")
else:
    print(max_sum)
    print(max_count)