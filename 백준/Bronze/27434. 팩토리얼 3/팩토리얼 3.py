import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def factorial(number):
    global ans
    ans *= number

    if number == n:
        return ans
    
    else:
        return factorial(number+1)

n = int(input())
ans = 1
if n == 0:
    print(1)
else:
    print(factorial(1))