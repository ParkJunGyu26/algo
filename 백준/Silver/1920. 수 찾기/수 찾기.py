import sys
input = sys.stdin.readline

def search(target):
    l, r = 0, len(A)-1
    while l <= r:
        mid = (l+r)//2
        if A[mid] == target:
            return 1
        elif A[mid] < target:
            l = mid + 1
        elif target < A[mid]:
            r = mid - 1
    
    return 0

N = int(input())
A = sorted(list(map(int, input().split())))
M = int(input())
check = list(map(int, input().split()))

for i in check:
    print(search(i))