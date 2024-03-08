#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

bool solution(vector<string> phone_book) {
    
    // 1. HashMap 만들기
    unordered_map<string, int> map;
    
    for (int i = 0; i < phone_book.size(); i++)
        map[phone_book[i]] = 1;
    
    // 2. 모든 전화번호의 substring이 HashMap에 존재 유무 파악
    for (int i = 0; i < phone_book.size(); i++)
    {
        for (int j = 0; j < phone_book[i].size() - 1; j++)
        {
            string phone_number = phone_book[i].substr(0, j + 1);
            if (map[phone_number])
                return false;
        }
    }
    
    // 3. 여기까지 오면 접두어 없음
    return true;
}