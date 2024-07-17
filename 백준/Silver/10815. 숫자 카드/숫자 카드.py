import sys
input = sys.stdin.readline

def binary_search(target):
    l, r = 0, n-1

    while l <= r:
        mid = (l+r)//2

        if N[mid] == target:
            return 1
        elif target < N[mid]:
            r = mid - 1
        elif N[mid] < target:
            l = mid + 1
    
    return 0

n = int(input())
N = sorted(list(map(int, input().split())))
m = int(input())
check = list(map(int, input().split()))

for i in check:
    print(binary_search(i), end =' ')