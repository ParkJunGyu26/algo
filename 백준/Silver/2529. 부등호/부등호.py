import sys
input = sys.stdin.readline

def backtracking():

    if len(ans) == n+1:
        for i in range(len(ans)-1):

            if sign[i] == '<':
                if ans[i] > ans[i+1]:
                    return
            else:
                if ans[i] < ans[i+1]:
                    return
        
        target = ''
        for i in ans:
            target += str(i)
        answer.append(target)
        return

    for i in range(10):
        if num[i]:
            num[i] -= 1
            ans.append(i)
            backtracking()
            ans.pop()
            num[i] += 1

num = [1 for i in range(10)]

n = int(input())
sign = list(input().rstrip().split())

ans = []
answer = []

backtracking()
print(answer[-1], answer[0], sep='\n')