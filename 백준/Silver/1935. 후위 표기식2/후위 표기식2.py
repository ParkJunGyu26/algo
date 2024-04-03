from collections import deque, defaultdict
import sys

input = sys.stdin.readline

cals = deque()
alphabet = {}
nums = []
check = True

n = int(input())
string = input().rstrip()

for s in string:
    if s == '*' or s == '-' or s == '+' or s == '/':
        continue
    
    alphabet[s] = 0

for i in alphabet:
    alphabet[i] = int(input())

for s in string:
    if s == '*' or s == '-' or s == '+' or s == '/':
        cals.append(s)
        if s == string[-1]:
            while cals:
                cal = cals.popleft()
                num1 = nums.pop()
                num2 = nums.pop()
                if cal == '*':
                    newNum = num2 * num1
                elif cal == '+':
                    newNum = num2 + num1
                elif cal == '-':
                    newNum = num2 - num1
                elif cal == '/':
                    newNum = num2 / num1

                nums.append(newNum)
        
        check = False
        continue
    
    if check:
        nums.append(alphabet[s])
    else:
        while cals:
            cal = cals.popleft()
            num1 = nums.pop()
            num2 = nums.pop()
            if cal == '*':
                newNum = num2 * num1
            elif cal == '+':
                newNum = num2 + num1
            elif cal == '-':
                newNum = num2 - num1
            elif cal == '/':
                newNum = num2 / num1
            
            nums.append(newNum)
        
        check = True
        nums.append(alphabet[s])

ans = nums[0]
ans_2 = f"{ans:.2f}"
print(ans_2)