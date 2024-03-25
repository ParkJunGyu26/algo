def solution(s):
    answer = True
    q = []
    
    for i in s:
        if len(q) == 0 and i == ')':
            answer = False
            return answer
        
        if i == '(':
            q.append(i)
            
        if i == ')':
            q.pop()
            
    if len(q) > 0:
        answer = False
    else:
        answer = True

    return answer