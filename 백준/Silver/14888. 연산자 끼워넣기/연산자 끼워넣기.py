import sys
input = sys.stdin.readline

def dfs():

    if len(cal) == sachik_sum:
        target = num[0]
        for i in range(1, len(cal)+1):
            if cal[i-1] == '+':
                target += num[i]
            elif cal[i-1] == '-':
                target -= num[i]
            elif cal[i-1] == '*':
                target *= num[i]
            else:
                if target < 0:
                    target = target * -1
                    target //= num[i]
                    target = target * -1
                else:
                    target //= num[i]
        ans.append(target)

        return

    # 연산자가 4개니깐 4번 돌리기
    for i in range(4):
        if sachik[i]:
            sachik[i] -= 1
            cal.append(s[i])

            dfs()

            sachik[i] += 1
            cal.pop()

n = int(input())
num = list(map(int, input().split()))
sachik = list(map(int, input().split()))
sachik_sum = sum(sachik)

s = {0:'+', 1:'-', 2:'*', 3:'/'}

cal = []
ans = []

dfs()
# print(len(ans))
# print("ans : ", ans)
print(max(ans))
print(min(ans))