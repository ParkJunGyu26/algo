import sys

input = sys.stdin.readline

stack = []
ans = []

sik = input().rstrip()

for i in sik:
    # 알파벳일 경우
    if 'A' <= i <= 'Z':
        ans.append(i)
    # 괄호, 연산자일 경우
    else:
        if i == '(':
            stack.append(i)

        elif i == ')':
            while stack:
                if stack[-1] == '(':
                    stack.pop()
                    break

                ans.append(stack.pop())
        
        elif i == '+' or i == '-':
            while stack:
                if stack[-1] == '+' or stack[-1] == '-' or stack[-1] == '*' or stack[-1] == '/':
                    ans.append(stack.pop())
                else:
                    break
            stack.append(i)
        
        elif i == '*' or i == '/':
            while stack:
                if stack[-1] == '*' or stack[-1] == '/':
                    ans.append(stack.pop())
                else:
                    break
            stack.append(i)

while stack:
    ans.append(stack.pop())

print(''.join(ans))