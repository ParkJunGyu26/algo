import sys
input = sys.stdin.readline

def binary_search(target, data):
    start = 0
    end = n - 1

    while start <= end:
        mid = (start + end) // 2

        if data[mid] == target:
            return True
        
        elif data[mid] < target:
            start = mid + 1
        else:
            end = mid - 1

    return False

n = int(input())
N = sorted(list(map(int, input().split())))

m = int(input())
M = list(map(int, input().split()))

for i in M:
    if binary_search(i, N):
        print(1)
    else:
        print(0)