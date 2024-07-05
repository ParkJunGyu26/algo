def number_check(s):
    check = False
    if len(s) == 4: check = True
    if len(s) == 6: check = True
    
    for i in s:
        if not ('0' <= i <= '9'):
            return False
    
    if check:
        return True
    else:
        return False

def solution(s):
    answer = number_check(s)
    return answer