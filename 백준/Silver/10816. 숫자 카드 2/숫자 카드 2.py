import sys
input = sys.stdin.readline

def binary_search(start, end, data, target):

    while start <= end:
        mid = (start + end) // 2

        if data[mid] == target:
            return ans[target]
        
        elif data[mid] < target:
            start = mid + 1
        else:
            end = mid - 1

n = int(input())
N = sorted(list(map(int, input().split())))

m = int(input())
M = list(map(int, input().split()))
ans = {}

for i in N:
    if i not in ans:
        ans[i] = 1
    else:
        ans[i] += 1

for i in M:
    result = binary_search(0, n-1, N, i)
    if result == None:
        print(0, end = ' ')
    else:
        print(result, end = ' ')