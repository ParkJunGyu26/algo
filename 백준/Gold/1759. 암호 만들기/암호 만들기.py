import sys
input = sys.stdin.readline

# cnt는 자음 개수
# V는 모음 리스트
def dfs(index, cnt):
    if len(ans) == l and cnt >= 2 and any(v in ans for v in V):
        print(*ans, sep="")

        return
    
    for i in range(index, c):
        if A[i] not in V:
            cnt += 1
        ans.append(A[i])
        dfs(i+1, cnt)
        ans.pop()
        if A[i] not in V:
            cnt -= 1


l, c = map(int, input().split())
A = sorted(list(input().rstrip().split()))
V = ['a', 'e', 'i', 'o', 'u']

ans = []

dfs(0, 0)