def solution(phone_book):
    dic = {}
    for p in phone_book:
        dic[p] = 1
    
    for p in phone_book:
        jubdoo = ""
        for number in p:
            jubdoo += number
            
            if jubdoo in dic and jubdoo != p:
                return False
    
    return True